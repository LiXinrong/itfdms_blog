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

package com.itfdms.upmsservice.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.vo.UserVO;
import com.itfdms.upmsservice.model.dto.UserDTO;
import com.itfdms.upmsservice.model.dto.UserInfo;
import com.itfdms.upmsservice.model.entity.SysUser;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service
 * @ClassName: SysUserService
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:43
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 方法实现说明
     *
     * @param username 用户名
     * @return userVo
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 根据用户名查询用户角色信息
     * @author lxr
     * @createDate 2018-08-30 18:44
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:44
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    UserVO findUserByUsername(String username);

    /**
     * 方法实现说明
     *
     * @param queryPage 查询条件
     * @param userVO
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 分页查询用户信息（含有角色信息）
     * @author lxr
     * @createDate 2018-08-30 18:44
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:44
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Page selectWithRolePage(QueryPage queryPage, UserVO userVO);

    /**
     * 方法实现说明
     *
     * @param userVo 角色名
     * @return userInfo
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 查询用户信息
     * @author lxr
     * @createDate 2018-08-30 18:45
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:45
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    UserInfo findUserInfo(UserVO userVo);


    /**
     * 方法实现说明
     *
     * @param randomStr 随机串
     * @param imageCode 验证码
     * @return : null
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 保存验证码
     * @author lxr
     * @createDate 2018-08-30 18:45
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:45
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    void saveImageCode(String randomStr, String imageCode);

    /**
     * 方法实现说明
     *
     * @param sysUser 用户
     * @return boolean
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 删除用户
     * @author lxr
     * @createDate 2018-08-30 18:45
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:45
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean deleteUserById(SysUser sysUser);

    /**
     * 方法实现说明
     *
     * @param userDto  用户信息
     * @param username 用户名
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 更新当前用户基本信息
     * @author lxr
     * @createDate 2018-08-30 18:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Result<Boolean> updateUserInfo(UserDTO userDto, String username);

    /**
     * 方法实现说明
     *
     * @param userDto  用户信息
     * @param username 用户信息
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 更新指定用户信息
     * @author lxr
     * @createDate 2018-08-30 18:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean updateUser(UserDTO userDto, String username);

    /**
     * 方法实现说明
     *
     * @param mobile 手机号
     * @return 用户信息
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 通过手机号查询用户信息
     * @author lxr
     * @createDate 2018-08-30 18:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    UserVO findUserByMobile(String mobile);

    /**
     * 发送验证码
     * @param mobile 手机号
     * @return R
     */
    /**
     * 方法实现说明
     *
     * @param mobile 手机号
     * @return Result
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 发送验证码
     * @author lxr
     * @createDate 2018-08-30 18:46
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:46
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Result<Boolean> sendSmsCode(String mobile);

    /**
     * 方法实现说明
     *
     * @param openId openId
     * @return 用户信息
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 通过openId查询用户
     * @author lxr
     * @createDate 2018-08-30 18:47
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:47
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    UserVO findUserByOpenId(String openId);

    /**
     * 方法实现说明
     *
     * @param id 用户ID
     * @return 用户信息
     * @throws
     * @className: SysUserService
     * @methodName
     * @description: 通过ID查询用户信息
     * @author lxr
     * @createDate 2018-08-30 18:47
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:47
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    UserVO selectUserVoById(Integer id);
}
