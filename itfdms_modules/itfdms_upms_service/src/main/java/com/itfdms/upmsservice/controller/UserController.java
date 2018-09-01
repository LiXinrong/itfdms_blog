/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
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
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.itfdms.upmsservice.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.itfdms.common.bean.config.FdfsPropertiesConfig;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.vo.UserVO;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.model.dto.UserDTO;
import com.itfdms.upmsservice.model.dto.UserInfo;
import com.itfdms.upmsservice.model.entity.SysUser;
import com.itfdms.upmsservice.model.entity.SysUserRole;
import com.itfdms.upmsservice.service.SysUserService;
import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.xiaoleilu.hutool.io.FileUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: UserController
 * @Description: 用户控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 22:14
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 22:14
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private SysUserService userService;
    @Autowired
    private FdfsPropertiesConfig fdfsPropertiesConfig;


    /**
     * 方法实现说明
     *
     * @param userVo 当前用户信息
     * @return 用户名
     * @throws
     * @className: UserController
     * @methodName
     * @description: 获取当前用户信息（角色、权限）
     * 并且异步初始化用户部门信息
     * @author lxr
     * @createDate 2018-08-30 22:15
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:15
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/info")
    public Result<UserInfo> user(UserVO userVo) {
        UserInfo userInfo = userService.findUserInfo(userVo);
        return new Result<>(userInfo);
    }

    /**
     * 方法实现说明
     *
     * @param id ID
     * @return 用户信息
     * @throws
     * @className: UserController
     * @methodName
     * @description: 通过ID查询当前用户信息
     * @author lxr
     * @createDate 2018-08-30 22:15
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:15
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/{id}")
    public UserVO user(@PathVariable Integer id) {
        return userService.selectUserVoById(id);
    }

    /**
     * 方法实现说明
     *
     * @param id IDesult
     * @return Result
     * @throws
     * @className: UserController
     * @methodName
     * @description: 删除用户信息
     * @author lxr
     * @createDate 2018-08-30 22:16
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:16
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @ApiOperation(value = "删除用户", notes = "根据ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("/{id}")
    public Result<Boolean> userDel(@PathVariable Integer id) {
        SysUser sysUser = userService.selectById(id);
        return new Result<>(userService.deleteUserById(sysUser));
    }

    /**
     * 方法实现说明
     *
     * @param userDto 用户信息
     * @return success/false
     * @throws
     * @className: UserController
     * @methodName
     * @description: 添加用户
     * @author lxr
     * @createDate 2018-08-30 22:16
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:16
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @PostMapping
    public Result<Boolean> user(@RequestBody UserDTO userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        sysUser.setDelFlag(CommonConstant.STATUS_NORMAL);
        sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
        userService.insert(sysUser);

        userDto.getRole().forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(roleId);
            userRole.insert();
        });
        return new Result<>(Boolean.TRUE);
    }

    /**
     * 方法实现说明
     *
     * @param userDto 用户信息
     * @return Result
     * @throws
     * @className: UserController
     * @methodName
     * @description: 更新用户信息
     * @author lxr
     * @createDate 2018-08-30 22:17
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:17
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @PutMapping
    public Result<Boolean> userUpdate(@RequestBody UserDTO userDto) {
        SysUser user = userService.selectById(userDto.getUserId());
        return new Result<>(userService.updateUser(userDto, user.getUsername()));
    }

    /**
     * 方法实现说明
     *
     * @param username 用户名
     * @return UseVo 对象
     * @throws
     * @className: UserController
     * @methodName
     * @description: 通过用户名查询用户及其角色信息
     * @author lxr
     * @createDate 2018-08-30 22:17
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:17
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/findUserByUsername/{username}")
    public UserVO findUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    /**
     * 方法实现说明
     *
     * @param mobile 手机号
     * @return UseVo 对象
     * @throws
     * @className: UserController
     * @methodName
     * @description: 通过手机号查询用户及其角色信息
     * @author lxr
     * @createDate 2018-08-30 22:18
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:18
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/findUserByMobile/{mobile}")
    public UserVO findUserByMobile(@PathVariable String mobile) {
        return userService.findUserByMobile(mobile);
    }

    /**
     * 方法实现说明
     *
     * @param openId openid
     * @return 对象
     * @throws
     * @className: UserController
     * @methodName
     * @description: 通过OpenId查询
     * @author lxr
     * @createDate 2018-08-30 22:18
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:18
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/findUserByOpenId/{openId}")
    public UserVO findUserByOpenId(@PathVariable String openId) {
        return userService.findUserByOpenId(openId);
    }

    /**
     * 方法实现说明
     *
     * @param params 参数集
     * @param userVO 用户信息
     * @throws
     * @className: UserController
     * @methodName
     * @description: 分页查询用户
     * @author lxr
     * @createDate 2018-08-30 22:18
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:18
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @RequestMapping("/userPage")
    public Page userPage(@RequestParam Map<String, Object> params, UserVO userVO) {
        return userService.selectWithRolePage(new QueryPage(params), userVO);
    }

    /**
     * 方法实现说明
     *
     * @param file 资源
     * @return filename map
     * @throws
     * @className: UserController
     * @methodName
     * @description: 上传用户头像
     * (多机部署有问题，建议使用独立的文件服务器)
     * @author lxr
     * @createDate 2018-08-30 22:18
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:18
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) {
        String fileExt = FileUtil.extName(file.getOriginalFilename());
        Map<String, String> resultMap = new HashMap<>(1);
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getBytes(), fileExt);
            resultMap.put("filename", fdfsPropertiesConfig.getFileHost() + storePath.getFullPath());
        } catch (IOException e) {
            logger.error("文件上传异常", e);
            throw new RuntimeException(e);
        }
        return resultMap;
    }

    /**
     * 方法实现说明
     *
     * @param userDto userDto
     * @param userVo  登录用户信息
     * @throws
     * @className: UserController
     * @methodName
     * @description: 修改个人信息
     * @author lxr
     * @createDate 2018-08-30 22:19
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:19
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PutMapping("/editInfo")
    public Result<Boolean> editInfo(@RequestBody UserDTO userDto, UserVO userVo) {
        return userService.updateUserInfo(userDto, userVo.getUserName());
    }
}
