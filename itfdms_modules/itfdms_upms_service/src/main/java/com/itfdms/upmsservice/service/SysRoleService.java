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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.itfdms.common.util.QueryPage;
import com.itfdms.upmsservice.model.dto.RoleDTO;
import com.itfdms.upmsservice.model.entity.SysRole;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service
 * @ClassName: SysRoleService
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:41
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
public interface SysRoleService extends IService<SysRole> {


    /**
     * 方法实现说明
     *
     * @param roleDto 角色信息
     * @return 成功、失败
     * @throws
     * @className: SysRoleService
     * @methodName
     * @description: 添加角色
     * @author lxr
     * @createDate 2018-08-30 18:41
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:41
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean insertRole(RoleDTO roleDto);

    /**
     * 方法实现说明
     *
     * @param objectQuery         查询条件
     * @param objectEntityWrapper wapper
     * @throws
     * @className: SysRoleService
     * @methodName
     * @description: 分页查角色列表
     * @author lxr
     * @createDate 2018-08-30 18:42
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:42
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Page selectwithDeptPage(QueryPage<Object> objectQuery, EntityWrapper<Object> objectEntityWrapper);

    /**
     * 方法实现说明
     *
     * @param roleDto 含有部门信息
     * @return 成功、失败
     * @throws
     * @className: SysRoleService
     * @methodName
     * @description: 更新角色
     * @author lxr
     * @createDate 2018-08-30 18:42
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:42
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean updateRoleById(RoleDTO roleDto);

    /**
     * 方法实现说明
     *
     * @param deptId 部门ID
     * @return 角色列表
     * @throws
     * @className: SysRoleService
     * @methodName
     * @description: 通过部门ID查询角色列表
     * @author lxr
     * @createDate 2018-08-30 18:42
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:42
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    List<SysRole> selectListByDeptId(Integer deptId);
}
