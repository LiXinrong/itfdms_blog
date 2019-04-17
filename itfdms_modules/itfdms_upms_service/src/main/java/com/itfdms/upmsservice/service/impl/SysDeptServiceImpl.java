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
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.upmsservice.common.util.TreeUtil;
import com.itfdms.upmsservice.mapper.SysDeptMapper;
import com.itfdms.upmsservice.mapper.SysDeptRelationMapper;
import com.itfdms.upmsservice.model.dto.DeptTree;
import com.itfdms.upmsservice.model.entity.SysDept;
import com.itfdms.upmsservice.model.entity.SysDeptRelation;
import com.itfdms.upmsservice.service.SysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service.impl
 * @ClassName: SysDeptServiceImpl
 * @Description: 部门管理 服务实现类
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:49
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysDeptRelationMapper sysDeptRelationMapper;

    /**
     * 方法实现说明
     *
     * @param dept 部门
     * @return
     * @throws
     * @className: SysDeptServiceImpl
     * @methodName
     * @description: 添加信息部门
     * @author lxr
     * @createDate 2018-08-30 21:40
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:40
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/


    @Override
    public Boolean insertDept(SysDept dept) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(dept, sysDept);
        this.insert(sysDept);
        this.insertDeptRelation(sysDept);
        return Boolean.TRUE;
    }

    /**
     * 方法实现说明
     *
     * @param sysDept 部门
     * @return : null
     * @throws
     * @className: SysDeptServiceImpl
     * @methodName
     * @description: 维护部门关系
     * @author lxr
     * @createDate 2018-08-30 21:40
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:40
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    private void insertDeptRelation(SysDept sysDept) {
        //增加部门关系表
        SysDeptRelation deptRelation = new SysDeptRelation();
        deptRelation.setDescendant(sysDept.getParentId());
        List<SysDeptRelation> deptRelationList = sysDeptRelationMapper.selectList(new EntityWrapper<>(deptRelation));
        for (SysDeptRelation sysDeptRelation : deptRelationList) {
            sysDeptRelation.setDescendant(sysDept.getDeptId());
            sysDeptRelationMapper.insert(sysDeptRelation);
        }
        //自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        sysDeptRelationMapper.insert(own);
    }

    /**
     * 方法实现说明
     *
     * @param id 部门 ID
     * @return 成功、失败
     * @throws
     * @className: SysDeptServiceImpl
     * @methodName
     * @description: 删除部门
     * @author lxr
     * @createDate 2018-08-30 21:40
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:40
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Boolean deleteDeptById(Integer id) {
        SysDept sysDept = new SysDept();
        sysDept.setDeptId(id);
        sysDept.setUpdateTime(new Date());
        sysDept.setDelFlag(CommonConstant.STATUS_DEL);
        this.deleteById(sysDept);
        sysDeptMapper.deleteDeptRealtion(id);
        return Boolean.TRUE;
    }

    /**
     * 方法实现说明
     *
     * @param sysDept 部门信息
     * @return 成功、失败
     * @throws
     * @className: SysDeptServiceImpl
     * @methodName
     * @description: 更新部门
     * @author lxr
     * @createDate 2018-08-30 21:41
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:41
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Boolean updateDeptById(SysDept sysDept) {
        //更新部门状态
        this.updateById(sysDept);
        //删除部门关系
        sysDeptMapper.deleteDeptRealtion(sysDept.getDeptId());
        //新建部门关系
        this.insertDeptRelation(sysDept);
        return null;
    }

    /**
     * 方法实现说明
     *
     * @param sysDeptEntityWrapper
     * @return 树
     * @throws
     * @className: SysDeptServiceImpl
     * @methodName
     * @description: 查询部门树
     * @author lxr
     * @createDate 2018-08-30 21:41
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:41
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public List<DeptTree> selectListTree(EntityWrapper<SysDept> sysDeptEntityWrapper) {
        return getDeptTree(this.selectList(sysDeptEntityWrapper), 0);
    }

    /**
     * 方法实现说明
     *
     * @param depts 部门
     * @param root  根节点
     * @throws
     * @className: SysDeptServiceImpl
     * @methodName
     * @description: 构建部门树
     * @author lxr
     * @createDate 2018-08-30 21:42
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:42
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    private List<DeptTree> getDeptTree(List<SysDept> depts, int root) {
        List<DeptTree> trees = new ArrayList<>();
        DeptTree node;
        for (SysDept dept : depts) {
            if (dept.getParentId().equals(dept.getDeptId())) {
                continue;
            }
            node = new DeptTree();
            node.setId(dept.getDeptId());
            node.setParentId(dept.getParentId());
            node.setName(dept.getName());
            trees.add(node);
        }
        return TreeUtil.build(trees, root);
    }
}
