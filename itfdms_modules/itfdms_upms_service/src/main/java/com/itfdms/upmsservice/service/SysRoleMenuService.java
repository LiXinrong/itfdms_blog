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

package com.itfdms.upmsservice.service;


import com.baomidou.mybatisplus.service.IService;
import com.itfdms.upmsservice.model.entity.SysRoleMenu;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service
 * @ClassName: SysRoleMenuService
 * @Description: 角色菜单表 服务类
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:40
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 方法实现说明
     *
     * @param role
     * @param roleId  角色
     * @param menuIds 菜单列表
     * @return : null
     * @throws
     * @className: SysRoleMenuService
     * @methodName
     * @description: 更新角色菜单
     * @author lxr
     * @createDate 2018-08-30 18:40
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:40
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean insertRoleMenus(String role, Integer roleId, Integer[] menuIds);
}
