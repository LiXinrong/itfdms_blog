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

package com.itfdms.upmsservice.model.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.model.entity
  * @ClassName:      SysDeptRelation
  * @Description:    java类作用描述
  * @Author:         lxr
  * @CreateDate:     2018-08-30 17:35
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 17:35
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/

@Data
@TableName("sys_dept_relation")
public class SysDeptRelation extends Model<SysDeptRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
	private Integer ancestor;
    /**
     * 后代节点
     */
	private Integer descendant;


	@Override
	protected Serializable pkVal() {
		return this.ancestor;
	}

	@Override
	public String toString() {
		return "SysDeptRelation{" +
			", ancestor=" + ancestor +
			", descendant=" + descendant +
			"}";
	}
}
