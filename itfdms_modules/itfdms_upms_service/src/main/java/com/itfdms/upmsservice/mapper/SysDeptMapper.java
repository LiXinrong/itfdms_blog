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

package com.itfdms.upmsservice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itfdms.upmsservice.model.entity.SysDept;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.mapper
 * @ClassName: SysDeptMapper
 * @Description: 部门管理 Mapper 接口
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:24
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 方法实现说明
     *
     * @param delFlag 删除标记
     * @return 数据列表
     * @throws
     * @className: SysDeptMapper
     * @methodName
     * @description: 关联dept——relation
     * @author lxr
     * @createDate 2018-08-30 18:32
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:32
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    List<SysDept> selectDeptDtoList(String delFlag);

    /**
     * 方法实现说明
     *
     * @param id 部门ID
     * @return : success/false
     * @throws
     * @className: SysDeptMapper
     * @methodName
     * @description: 删除部门关系表数据
     * @author lxr
     * @createDate 2018-08-30 18:32
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:32
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    void deleteDeptRealtion(Integer id);
}