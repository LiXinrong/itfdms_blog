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

package com.itfdms.upmsservice.model.dto;

import lombok.Data;


/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.model.dto
  * @ClassName:      DeptTree
  * @Description:    部门树
  * @Author:         lxr
  * @CreateDate:     2018-08-30 17:32
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 17:32
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/

@Data
public class DeptTree extends TreeNode {
    private String name;
}