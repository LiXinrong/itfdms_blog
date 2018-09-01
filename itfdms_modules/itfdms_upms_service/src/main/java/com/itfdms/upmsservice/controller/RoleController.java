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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.model.dto.RoleDTO;
import com.itfdms.upmsservice.model.entity.SysRole;
import com.itfdms.upmsservice.service.SysRoleMenuService;
import com.itfdms.upmsservice.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: RoleController
 * @Description: 角色控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 22:11
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 22:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    /**
     * 方法实现说明
     *
     * @param id ID
     * @return 角色信息
     * @throws
     * @className: RoleController
     * @methodName
     * @description: 通过ID查询角色信息
     * @author lxr
     * @createDate 2018-08-30 22:09
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:09
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @GetMapping("/{id}")
    public SysRole role(@PathVariable Integer id) {
        return sysRoleService.selectById(id);
    }

    /**
     * 方法实现说明
     *
     * @param roleDto 角色信息
     * @return success、false
     * @throws
     * @className: RoleController
     * @methodName
     * @description: 添加角色
     * @author lxr
     * @createDate 2018-08-30 22:10
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:10
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @PostMapping
    public Result<Boolean> role(@RequestBody RoleDTO roleDto) {
        return new Result<>(sysRoleService.insertRole(roleDto));
    }

    /**
     * 方法实现说明
     *
     * @param roleDto 角色信息
     * @return success/false
     * @throws
     * @className: RoleController
     * @methodName
     * @description: 修改角色
     * @author lxr
     * @createDate 2018-08-30 22:10
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:10
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @PutMapping
    public Result<Boolean> roleUpdate(@RequestBody RoleDTO roleDto) {
        return new Result<>(sysRoleService.updateRoleById(roleDto));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> roleDel(@PathVariable Integer id) {
        SysRole sysRole = sysRoleService.selectById(id);
        sysRole.setDelFlag(CommonConstant.STATUS_DEL);
        return new Result<>(sysRoleService.updateById(sysRole));
    }


    /**
     * 方法实现说明
     *
     * @param deptId 部门ID
     * @return 角色列表
     * @throws
     * @className: RoleController
     * @methodName
     * @description: 获取角色列表
     * @author lxr
     * @createDate 2018-08-30 22:10
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:10
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @GetMapping("/roleList/{deptId}")
    public List<SysRole> roleList(@PathVariable Integer deptId) {
        return sysRoleService.selectListByDeptId(deptId);

    }


    /**
     * 方法实现说明
     *
     * @param params 分页对象
     * @return 分页对象
     * @throws
     * @className: RoleController
     * @methodName
     * @description: 分页查询角色信息
     * @author lxr
     * @createDate 2018-08-30 22:10
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:10
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @RequestMapping("/rolePage")
    public Page rolePage(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysRoleService.selectwithDeptPage(new QueryPage<>(params), new EntityWrapper<>());
    }

    /**
     * 方法实现说明
     *
     * @param roleId  角色ID
     * @param menuIds 菜单结合
     * @throws
     * @className: RoleController
     * @methodName
     * @description: 更新角色菜单
     * @author lxr
     * @createDate 2018-08-30 22:11
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:11
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @PutMapping("/roleMenuUpd")
    public Result<Boolean> roleMenuUpd(Integer roleId, @RequestParam("menuIds[]") Integer[] menuIds) {
        SysRole sysRole = sysRoleService.selectById(roleId);
        return new Result<>(sysRoleMenuService.insertRoleMenus(sysRole.getRoleCode(), roleId, menuIds));
    }
}
