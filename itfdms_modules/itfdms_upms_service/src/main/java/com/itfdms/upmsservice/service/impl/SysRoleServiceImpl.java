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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.common.util.QueryPage;
import com.itfdms.upmsservice.mapper.SysRoleDeptMapper;
import com.itfdms.upmsservice.mapper.SysRoleMapper;
import com.itfdms.upmsservice.model.dto.RoleDTO;
import com.itfdms.upmsservice.model.entity.SysRole;
import com.itfdms.upmsservice.model.entity.SysRoleDept;
import com.itfdms.upmsservice.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service.impl
 * @ClassName: SysRoleServiceImpl
 * @Description: 服务实现类
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:54
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 方法实现说明
     *
     * @param roleDto 角色信息
     * @return 成功、失败
     * @throws
     * @className: SysRoleServiceImpl
     * @methodName
     * @description: 添加角色
     * @author lxr
     * @createDate 2018-08-30 21:51
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:51
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Boolean insertRole(RoleDTO roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        sysRoleMapper.insert(sysRole);
        SysRoleDept roleDept = new SysRoleDept();
        roleDept.setRoleId(sysRole.getRoleId());
        roleDept.setDeptId(roleDto.getRoleDeptId());
        sysRoleDeptMapper.insert(roleDept);
        return true;
    }

    /**
     * 方法实现说明
     *
     * @param query   查询条件
     * @param wrapper wapper
     * @throws
     * @className: SysRoleServiceImpl
     * @methodName
     * @description: 分页查角色列表
     * @author lxr
     * @createDate 2018-08-30 21:51
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:51
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Page selectwithDeptPage(QueryPage<Object> query, EntityWrapper<Object> wrapper) {
        query.setRecords(sysRoleMapper.selectRolePage(query, query.getCondition()));
        return query;
    }

    /**
     * 方法实现说明
     *
     * @param roleDto 含有部门信息
     * @return 成功、失败
     * @throws
     * @className: SysRoleServiceImpl
     * @methodName
     * @description: 更新角色
     * @author lxr
     * @createDate 2018-08-30 21:51
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:51
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateRoleById(RoleDTO roleDto) {
        //删除原有的角色部门关系
        SysRoleDept condition = new SysRoleDept();
        condition.setRoleId(roleDto.getRoleId());
        sysRoleDeptMapper.delete(new EntityWrapper<>(condition));

        //更新角色信息
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        sysRoleMapper.updateById(sysRole);

        //维护角色部门关系
        SysRoleDept roleDept = new SysRoleDept();
        roleDept.setRoleId(sysRole.getRoleId());
        roleDept.setDeptId(roleDto.getRoleDeptId());
        sysRoleDeptMapper.insert(roleDept);
        return true;
    }

    /**
     * 方法实现说明
     *
     * @param deptId 部门ID
     * @return 角色列表
     * @throws
     * @className: SysRoleServiceImpl
     * @methodName
     * @description: 通过部门ID查询角色列表
     * @author lxr
     * @createDate 2018-08-30 21:52
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:52
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public List<SysRole> selectListByDeptId(Integer deptId) {
        return sysRoleMapper.selectListByDeptId(deptId);
    }
}
