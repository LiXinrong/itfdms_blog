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

package com.itfdms.upmsservice.model.dto;

import com.itfdms.upmsservice.model.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.model.dto
  * @ClassName:      UserInfo
  * @Description:    java类作用描述
  * commit('SET_ROLES', data)
  * commit('SET_NAME', data)
  * commit('SET_AVATAR', data)
  * commit('SET_INTRODUCTION', data)
  * commit('SET_PERMISSIONS', data)
  * @Author:         lxr
  * @CreateDate:     2018-08-30 17:34
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 17:34
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/


@Data
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;
}
