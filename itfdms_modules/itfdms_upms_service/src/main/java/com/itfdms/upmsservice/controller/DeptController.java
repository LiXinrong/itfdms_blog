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
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.model.dto.DeptTree;
import com.itfdms.upmsservice.model.entity.SysDept;
import com.itfdms.upmsservice.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: DeptController
 * @Description: 部门管理 前端控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 21:56
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 21:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 方法实现说明
     *
     * @param id ID
     * @return SysDept
     * @throws
     * @className: DeptController
     * @methodName
     * @description: 通过ID查询
     * @author lxr
     * @createDate 2018-08-30 21:57
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:57
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/{id}")
    public SysDept get(@PathVariable Integer id) {
        return sysDeptService.selectById(id);
    }


    /**
     * 方法实现说明
     *
     * @return 树形菜单
     * @throws
     * @className: DeptController
     * @methodName
     * @description: 返回树形菜单集合
     * @author lxr
     * @createDate 2018-08-30 21:57
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:57
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping(value = "/tree")
    public List<DeptTree> getTree() {
        SysDept condition = new SysDept();
        condition.setDelFlag(CommonConstant.STATUS_NORMAL);
        return sysDeptService.selectListTree(new EntityWrapper<>(condition));
    }

    /**
     * 方法实现说明
     *
     * @param sysDept 实体
     * @return success/false
     * @throws
     * @className: DeptController
     * @methodName
     * @description: 添加
     * @author lxr
     * @createDate 2018-08-30 21:58
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:58
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PostMapping
    public Boolean add(@RequestBody SysDept sysDept) {
        return sysDeptService.insertDept(sysDept);
    }


    /**
     * 方法实现说明
     *
     * @param id ID
     * @return success/false
     * @throws
     * @className: DeptController
     * @methodName
     * @description: 删除
     * @author lxr
     * @createDate 2018-08-30 21:58
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:58
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return sysDeptService.deleteDeptById(id);
    }


    /**
     * 方法实现说明
     *
     * @param sysDept 实体
     * @return success/false
     * @throws
     * @className: DeptController
     * @methodName
     * @description: 编辑
     * @author lxr
     * @createDate 2018-08-30 21:58
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:58
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PutMapping
    public Boolean edit(@RequestBody SysDept sysDept) {
        sysDept.setUpdateTime(new Date());
        return sysDeptService.updateDeptById(sysDept);
    }
}
