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

package com.itfdms.upmsservice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itfdms.common.bean.interceptor.DataScope;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.vo.UserVO;
import com.itfdms.upmsservice.model.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.github.pig.admin.mapper
 * @ClassName: SysUserMapper
 * @Description: 用户表 Mapper 接口
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:28
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 方法实现说明
     *
     * @param username : 用户名
     * @return : userVO
     * @throws
     * @className: SysUserMapper
     * @methodName selectUserVoByUsername
     * @description: 通过用户名查询用户信息（含有角色信息）
     * @author lxr
     * @createDate 2018-08-30 18:30
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:30
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    UserVO selectUserVoByUsername(String username);

    /**
     * 方法实现说明
     *
     * @param queryPage 查询条件
     * @param username  用户名
     * @throws
     * @className: SysUserMapper
     * @methodName
     * @description: 分页查询用户信息（含角色）
     * @author lxr
     * @createDate 2018-08-30 18:30
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:30
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    List selectUserVoPageDataScope(QueryPage queryPage, @Param("username") Object username, DataScope dataScope);

    /**
      * 方法实现说明
      * @className:      SysUserMapper
      * @methodName
      * @description:    通过手机号查询用户信息（含有角色信息）
      * @author          lxr
      * @createDate      2018-08-30 18:31
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 18:31
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      *  @param mobile 用户名
      *  @return userVo
      * @exception
    **/

    UserVO selectUserVoByMobile(String mobile);

    /**
      * 方法实现说明
      * @className:      SysUserMapper
      * @methodName
      * @description:    通过openId查询用户信息
      * @author          lxr
      * @createDate      2018-08-30 18:31
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 18:31
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param openId openid
      * @return userVo
      * @exception
    **/

    UserVO selectUserVoByOpenId(String openId);

    /**
      * 方法实现说明
      * @className:      SysUserMapper
      * @methodName
      * @description:    通过ID查询用户信息
      * @author          lxr
      * @createDate      2018-08-30 18:32
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 18:32
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param id 用户ID
      * @return userVo
      * @exception
    **/

    UserVO selectUserVoById(Integer id);
}