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

import com.baomidou.mybatisplus.service.IService;
import com.itfdms.common.entity.SysLog;

/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.service
  * @ClassName:      SysLogService
  * @Description:    日志表 服务类
  * @Author:         lxr
  * @CreateDate:     2018-08-30 18:39
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 18:39
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/
public interface SysLogService extends IService<SysLog> {

    /**
      * 方法实现说明
      * @className:      SysLogService
      * @methodName
      * @description:    通过ID删除日志（逻辑删除）
      * @author          lxr
      * @createDate      2018-08-30 18:39
      * @updateUser:     lxr
      * @updateDate:     2018-08-30 18:39
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param id 日志ID
      * @return true/false
      * @exception
    **/

    Boolean updateByLogId(Long id);
}
