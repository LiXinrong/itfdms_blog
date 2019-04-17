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

package com.itfdms.upmsservice.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: LogController
 * @Description: 日志表 前端控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 22:03
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 22:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@RestController
@RequestMapping("/log")
public class LogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;


    /**
     * 方法实现说明
     *
     * @param params 分页对象
     * @return 分页对象
     * @throws
     * @className: LogController
     * @methodName
     * @description: 分页查询日志信息
     * @author lxr
     * @createDate 2018-08-30 22:04
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:04
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @RequestMapping("/logPage")
    public Page logPage(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysLogService.selectPage(new QueryPage<>(params), new EntityWrapper<>());
    }


    /**
     * 方法实现说明
     *
     * @param id ID
     * @return success/false
     * @throws
     * @className: LogController
     * @methodName
     * @description: 根据ID
     * @author lxr
     * @createDate 2018-08-30 22:04
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:04
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return new Result<>(sysLogService.updateByLogId(id));
    }
}
