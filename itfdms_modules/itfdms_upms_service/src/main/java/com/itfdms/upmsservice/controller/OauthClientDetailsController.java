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
import com.baomidou.mybatisplus.plugins.Page;
import com.itfdms.common.util.QueryPage;
import com.itfdms.common.util.Result;
import com.itfdms.common.web.BaseController;
import com.itfdms.upmsservice.model.entity.SysOauthClientDetails;
import com.itfdms.upmsservice.service.SysOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: OauthClientDetailsController
 * @Description: 前端控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 22:07
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 22:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@RestController
@RequestMapping("/client")
public class OauthClientDetailsController extends BaseController {

    @Autowired
    private SysOauthClientDetailsService sysOauthClientDetailsService;


    /**
     * 方法实现说明
     *
     * @param id ID
     * @return SysOauthClientDetails
     * @throws
     * @className: OauthClientDetailsController
     * @methodName
     * @description: 通过ID查询
     * @author lxr
     * @createDate 2018-08-30 22:06
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:06
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/{id}")
    public SysOauthClientDetails get(@PathVariable Integer id) {
        return sysOauthClientDetailsService.selectById(id);
    }


    /**
     * 方法实现说明
     *
     * @param params 分页对象
     * @return 分页对象
     * @throws
     * @className: OauthClientDetailsController
     * @methodName
     * @description: 分页查询信息
     * @author lxr
     * @createDate 2018-08-30 22:06
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:06
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @RequestMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        return sysOauthClientDetailsService.selectPage(new QueryPage<>(params), new EntityWrapper<>());
    }

    /**
     * 方法实现说明
     *
     * @param sysOauthClientDetails 实体
     * @return success/false
     * @throws
     * @className: OauthClientDetailsController
     * @methodName
     * @description: 添加
     * @author lxr
     * @createDate 2018-08-30 22:06
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:06
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PostMapping
    public Result<Boolean> add(@RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return new Result<>(sysOauthClientDetailsService.insert(sysOauthClientDetails));
    }

    /**
     * 方法实现说明
     *
     * @param id ID
     * @return success/false
     * @throws
     * @className: OauthClientDetailsController
     * @methodName
     * @description: 删除
     * @author lxr
     * @createDate 2018-08-30 22:06
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:06
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable String id) {
        SysOauthClientDetails sysOauthClientDetails = new SysOauthClientDetails();
        sysOauthClientDetails.setClientId(id);
        return new Result<>(sysOauthClientDetailsService.deleteById(sysOauthClientDetails));
    }

    /**
     * 方法实现说明
     *
     * @param sysOauthClientDetails 实体
     * @return success/false
     * @throws
     * @className: OauthClientDetailsController
     * @methodName
     * @description: 编辑
     * @author lxr
     * @createDate 2018-08-30 22:07
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:07
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PutMapping
    public Result<Boolean> edit(@RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return new Result<>(sysOauthClientDetailsService.updateById(sysOauthClientDetails));
    }
}
