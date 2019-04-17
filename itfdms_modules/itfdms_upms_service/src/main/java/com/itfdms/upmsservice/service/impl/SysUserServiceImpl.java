/*
 *    Copyright (c) 2018-2025, lxr All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lxr (wangiegie@gmail.com)
 */

package com.itfdms.upmsservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.common.bean.interceptor.DataScope;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.constant.MqQueueConstant;
import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.constant.enums.EnumSmsChannelTemplate;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.util.mobile.MobileMsgTemplate;
import com.itfdms.common.vo.MenuVO;
import com.itfdms.common.vo.SysRole;
import com.itfdms.common.vo.UserVO;
import com.itfdms.upmsservice.mapper.SysUserMapper;
import com.itfdms.upmsservice.model.dto.UserDTO;
import com.itfdms.upmsservice.model.dto.UserInfo;
import com.itfdms.upmsservice.model.entity.SysDeptRelation;
import com.itfdms.upmsservice.model.entity.SysUser;
import com.itfdms.upmsservice.model.entity.SysUserRole;
import com.itfdms.upmsservice.service.SysDeptRelationService;
import com.itfdms.upmsservice.service.SysMenuService;
import com.itfdms.upmsservice.service.SysUserRoleService;
import com.itfdms.upmsservice.service.SysUserService;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service.impl
 * @ClassName: SysUserServiceImpl
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:55
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptRelationService sysDeptRelationService;

    @Override
    public UserInfo findUserInfo(UserVO userVo) {
        SysUser condition = new SysUser();
        condition.setUsername(userVo.getUserName());
        SysUser sysUser = this.selectOne(new EntityWrapper<>(condition));

        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表
        List<SysRole> roleList = userVo.getRoleList();
        List<String> roleNames = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleList)) {
            for (SysRole sysRole : roleList) {
                if (!StrUtil.equals(SecurityConstants.BASE_ROLE, sysRole.getRoleName())) {
                    roleNames.add(sysRole.getRoleName());
                }
            }
        }
        String[] roles = roleNames.toArray(new String[roleNames.size()]);
        userInfo.setRoles(roles);

        //设置权限列表（menu.permission）
        Set<MenuVO> menuVoSet = new HashSet<>();
        for (String role : roles) {
            List<MenuVO> menuVos = sysMenuService.findMenuByRoleName(role);
            menuVoSet.addAll(menuVos);
        }
        Set<String> permissions = new HashSet<>();
        for (MenuVO menuVo : menuVoSet) {
            if (StringUtils.isNotEmpty(menuVo.getPermission())) {
                String permission = menuVo.getPermission();
                permissions.add(permission);
            }
        }
        userInfo.setPermissions(permissions.toArray(new String[permissions.size()]));
        return userInfo;
    }

    @Override
    @Cacheable(value = "user_details", key = "#username")
    public UserVO findUserByUsername(String username) {
        return sysUserMapper.selectUserVoByUsername(username);
    }

    /**
     * 方法实现说明
     *
     * @param mobile 手机号
     * @return 用户信息
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 通过手机号查询用户信息
     * @author lxr
     * @createDate 2018-08-30 21:45
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:45
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @Override
    @Cacheable(value = "user_details_mobile", key = "#mobile")
    public UserVO findUserByMobile(String mobile) {
        return sysUserMapper.selectUserVoByMobile(mobile);
    }

    /**
     * 方法实现说明
     *
     * @param openId openId
     * @return 用户信息
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 通过openId查询用户
     * @author lxr
     * @createDate 2018-08-30 21:45
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:45
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    @Cacheable(value = "user_details_openid", key = "#openId")
    public UserVO findUserByOpenId(String openId) {
        return sysUserMapper.selectUserVoByOpenId(openId);
    }

    @Override
    public Page selectWithRolePage(QueryPage query, UserVO userVO) {
        DataScope dataScope = new DataScope();
        dataScope.setScopeName("deptId");
        dataScope.setIsOnly(true);
        dataScope.setDeptIds(getChildDepts(userVO));
        Object username = query.getCondition().get("username");
        query.setRecords(sysUserMapper.selectUserVoPageDataScope(query, username, dataScope));
        return query;
    }

    /**
     * 方法实现说明
     *
     * @param id 用户ID
     * @return 用户信息
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 通过ID查询用户信息
     * @author lxr
     * @createDate 2018-08-30 21:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public UserVO selectUserVoById(Integer id) {
        return sysUserMapper.selectUserVoById(id);
    }

    /**
     * 方法实现说明
     *
     * @param randomStr 客户端生成
     * @param imageCode 验证码信息
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 保存用户验证码，和randomStr绑定
     * @author lxr
     * @createDate 2018-08-30 21:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public void saveImageCode(String randomStr, String imageCode) {
        redisTemplate.opsForValue().set(SecurityConstants.DEFAULT_CODE_KEY + randomStr, imageCode, Long.parseLong(SecurityConstants.DEFAULT_IMAGE_EXPIRE), TimeUnit.SECONDS);
    }


    /**
     * 方法实现说明
     *
     * @param mobile 手机号
     * @return true、false
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 发送验证码
     * 1. 先去redis 查询是否 60S内已经发送
     * 2. 未发送： 判断手机号是否存 ? false :产生4位数字  手机号-验证码
     * 3. 发往消息中心-》发送信息
     * 4. 保存redis
     * @author lxr
     * @createDate 2018-08-30 21:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Result<Boolean> sendSmsCode(String mobile) {
        Object tempCode = redisTemplate.opsForValue().get(SecurityConstants.DEFAULT_CODE_KEY + mobile);
        if (tempCode != null) {
            log.error("用户:{}验证码未失效{}", mobile, tempCode);
            return new Result<>(false, "验证码未失效，请失效后再次申请");
        }

        SysUser params = new SysUser();
        params.setPhone(mobile);
        List<SysUser> userList = this.selectList(new EntityWrapper<>(params));

        if (CollectionUtil.isEmpty(userList)) {
            log.error("根据用户手机号{}查询用户为空", mobile);
            return new Result<>(false, "手机号不存在");
        }

        String code = RandomUtil.randomNumbers(4);
        JSONObject contextJson = new JSONObject();
        contextJson.put("code", code);
        contextJson.put("product", "Pig4Cloud");
        log.info("短信发送请求消息中心 -> 手机号:{} -> 验证码：{}", mobile, code);
        rabbitTemplate.convertAndSend(MqQueueConstant.MOBILE_CODE_QUEUE,
                new MobileMsgTemplate(
                        mobile,
                        contextJson.toJSONString(),
                        CommonConstant.ALIYUN_SMS,
                        EnumSmsChannelTemplate.LOGIN_NAME_LOGIN.getSignName(),
                        EnumSmsChannelTemplate.LOGIN_NAME_LOGIN.getTemplate()
                ));
        redisTemplate.opsForValue().set(SecurityConstants.DEFAULT_CODE_KEY + mobile, code, Long.parseLong(SecurityConstants.DEFAULT_IMAGE_EXPIRE), TimeUnit.SECONDS);
        return new Result<>(true);
    }

    /**
     * 方法实现说明
     *
     * @param sysUser 用户
     * @return Boolean
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 删除用户
     * @author lxr
     * @createDate 2018-08-30 21:47
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:47
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    @CacheEvict(value = "user_details", key = "#sysUser.username")
    public Boolean deleteUserById(SysUser sysUser) {
        sysUserRoleService.deleteByUserId(sysUser.getUserId());
        this.deleteById(sysUser.getUserId());
        return Boolean.TRUE;
    }

    @Override
    @CacheEvict(value = "user_details", key = "#username")
    public Result<Boolean> updateUserInfo(UserDTO userDto, String username) {
        UserVO userVo = this.findUserByUsername(username);
        SysUser sysUser = new SysUser();
        if (StrUtil.isNotBlank(userDto.getPassword())
                && StrUtil.isNotBlank(userDto.getNewpassword1())) {
            if (ENCODER.matches(userDto.getPassword(), userVo.getPassWord())) {
                sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
            } else {
                log.warn("原密码错误，修改密码失败:{}", username);
                return new Result<>(Boolean.FALSE, "原密码错误，修改失败");
            }
        }
        sysUser.setPhone(userDto.getPhone());
        sysUser.setUserId(userVo.getUserId());
        sysUser.setAvatar(userDto.getAvatar());
        return new Result<>(this.updateById(sysUser));
    }

    @Override
    @CacheEvict(value = "user_details", key = "#username")
    public Boolean updateUser(UserDTO userDto, String username) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setUpdateTime(new Date());
        this.updateById(sysUser);

        SysUserRole condition = new SysUserRole();
        condition.setUserId(userDto.getUserId());
        sysUserRoleService.delete(new EntityWrapper<>(condition));
        userDto.getRole().forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(roleId);
            userRole.insert();
        });
        return Boolean.TRUE;
    }

    /**
     * 方法实现说明
     *
     * @param userVO 用户信息
     * @return 子部门列表
     * @throws
     * @className: SysUserServiceImpl
     * @methodName
     * @description: 获取当前用户的子部门信息
     * @author lxr
     * @createDate 2018-08-30 21:49
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:49
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    private List<Integer> getChildDepts(UserVO userVO) {
        UserVO userVo = findUserByUsername(userVO.getUserName());
        Integer deptId = Integer.parseInt(userVo.getDeptId());

        //获取当前部门的子部门
        SysDeptRelation deptRelation = new SysDeptRelation();
        deptRelation.setAncestor(deptId);
        List<SysDeptRelation> deptRelationList = sysDeptRelationService.selectList(new EntityWrapper<>(deptRelation));
        List<Integer> deptIds = new ArrayList<>();
        for (SysDeptRelation sysDeptRelation : deptRelationList) {
            deptIds.add(sysDeptRelation.getDescendant());
        }
        return deptIds;
    }

}
