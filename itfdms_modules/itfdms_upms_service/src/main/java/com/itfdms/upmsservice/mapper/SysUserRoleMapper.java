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
import com.itfdms.upmsservice.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;


/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.mapper
  * @ClassName:      SysUserRoleMapper
  * @Description:    用户角色表 Mapper 接口
  * @Author:         lxr
  * @CreateDate:     2018-08-30 18:28
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 18:28
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {


    /**
      * 方法实现说明
      * @className:      SysUserRoleMapper
      * @methodName      deleteByUserId
      * @description:     根据用户Id删除该用户的角色关系
      * @author          lxr
      * @createDate      2018-08-30 18:29
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 18:29
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param userId : 用户ID
      * @return : success/false
      * @exception
    **/

    Boolean deleteByUserId(@Param("userId") Integer userId);
}