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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.itfdms.upmsservice.model.dto.DeptTree;
import com.itfdms.upmsservice.model.entity.SysDept;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service
 * @ClassName: SysDeptService
 * @Description: 部门管理 服务类
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:37
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
public interface SysDeptService extends IService<SysDept> {

    /**
     * 方法实现说明
     *
     * @param sysDeptEntityWrapper
     * @return 树
     * @throws
     * @className: SysDeptService
     * @methodName
     * @description: 查询部门树菜单
     * @author lxr
     * @createDate 2018-08-30 18:37
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:37
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    List<DeptTree> selectListTree(EntityWrapper<SysDept> sysDeptEntityWrapper);

    /**
     * 方法实现说明
     *
     * @param sysDept
     * @return
     * @throws
     * @className: SysDeptService
     * @methodName
     * @description: 添加信息部门
     * @author lxr
     * @createDate 2018-08-30 18:38
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:38
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean insertDept(SysDept sysDept);

    /**
     * 方法实现说明
     *
     * @param id 部门 ID
     * @return 成功、失败
     * @throws
     * @className: SysDeptService
     * @methodName
     * @description: 删除部门
     * @author lxr
     * @createDate 2018-08-30 18:38
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:38
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean deleteDeptById(Integer id);

    /**
     * 方法实现说明
     *
     * @param sysDept 部门信息
     * @return 成功、失败
     * @throws
     * @className: SysDeptService
     * @methodName
     * @description: 更新部门
     * @author lxr
     * @createDate 2018-08-30 18:38
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:38
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean updateDeptById(SysDept sysDept);
}
