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
import com.itfdms.common.entity.SysZuulRoute;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.service.SysZuulRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: ZuulRouteController
 * @Description: 动态路由配置表 前端控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 22:27
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 22:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@RestController
@RequestMapping("/route")
public class ZuulRouteController extends BaseController {
    @Autowired
    private SysZuulRouteService sysZuulRouteService;

    /**
     * 方法实现说明
     *
     * @param id
     * @return SysZuulRoute
     * @throws
     * @className: ZuulRouteController
     * @methodName
     * @description: 通过ID查询
     * @author lxr
     * @createDate 2018-08-30 22:28
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:28
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/{id}")
    public SysZuulRoute get(@PathVariable Integer id) {
        return sysZuulRouteService.selectById(id);
    }

    /**
     * 方法实现说明
     *
     * @param params 分页对象
     * @return 分页对象
     * @throws
     * @className: ZuulRouteController
     * @methodName
     * @description: 分页查询信息
     * @author lxr
     * @createDate 2018-08-30 22:28
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:28
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @RequestMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysZuulRouteService.selectPage(new QueryPage<>(params), new EntityWrapper<>());
    }

    /**
     * 方法实现说明
     *
     * @param sysZuulRoute 实体
     * @return success/false
     * @throws
     * @className: ZuulRouteController
     * @methodName
     * @description: 添加
     * @author lxr
     * @createDate 2018-08-30 22:29
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:29
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PostMapping
    public Result<Boolean> add(@RequestBody SysZuulRoute sysZuulRoute) {
        return new Result<>(sysZuulRouteService.insert(sysZuulRoute));
    }


    /**
     * 方法实现说明
     *
     * @param id ID
     * @return success/false
     * @throws
     * @className: ZuulRouteController
     * @methodName
     * @description: 删除
     * @author lxr
     * @createDate 2018-08-30 22:29
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:29
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        SysZuulRoute sysZuulRoute = new SysZuulRoute();
        sysZuulRoute.setServiceId(id);
        sysZuulRoute.setUpdateTime(new Date());
        sysZuulRoute.setDelFlag(CommonConstant.STATUS_DEL);
        return new Result<>(sysZuulRouteService.updateById(sysZuulRoute));
    }


    /**
     * 方法实现说明
     *
     * @param sysZuulRoute 实体
     * @return success/false
     * @throws
     * @className: ZuulRouteController
     * @methodName
     * @description: 编辑
     * @author lxr
     * @createDate 2018-08-30 22:29
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:29
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PutMapping
    public Result<Boolean> edit(@RequestBody SysZuulRoute sysZuulRoute) {
        sysZuulRoute.setUpdateTime(new Date());
        return new Result<>(sysZuulRouteService.updateById(sysZuulRoute));
    }


    /**
     * 方法实现说明
     *
     * @return success/fasle
     * @throws
     * @className: ZuulRouteController
     * @methodName
     * @description: 刷新配置
     * @author lxr
     * @createDate 2018-08-30 22:29
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:29
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @GetMapping("/apply")
    public Result<Boolean> apply() {
        return new Result<>(sysZuulRouteService.applyZuulRoute());
    }
}
