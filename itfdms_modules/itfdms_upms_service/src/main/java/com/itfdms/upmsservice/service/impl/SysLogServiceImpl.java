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

package com.itfdms.upmsservice.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.entity.SysLog;
import com.itfdms.common.util.Assert;
import com.itfdms.upmsservice.mapper.SysLogMapper;
import com.itfdms.upmsservice.service.SysLogService;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.service.impl
  * @ClassName:      SysLogServiceImpl
  * @Description:    日志表 服务实现类
  * @Author:         lxr
  * @CreateDate:     2018-08-30 21:53
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 21:53
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public Boolean updateByLogId(Long id) {
        Assert.isNull(id, "日志ID为空");

        SysLog sysLog = new SysLog();
        sysLog.setId(id);
        sysLog.setDelFlag(CommonConstant.STATUS_DEL);
        sysLog.setUpdateTime(new Date());
        return updateById(sysLog);
    }
}
