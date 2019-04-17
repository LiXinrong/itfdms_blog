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
import com.itfdms.upmsservice.model.entity.SysDict;
import com.itfdms.upmsservice.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: DictController
 * @Description: 字典表 前端控制器
 * @Author: lxr
 * @CreateDate: 2018-08-30 21:59
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 21:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/


@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 方法实现说明
     *
     * @param id ID
     * @return 字典信息
     * @throws
     * @className: DictController
     * @methodName
     * @description: 通过ID查询字典信息
     * @author lxr
     * @createDate 2018-08-30 22:00
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:00
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/{id}")
    public SysDict dict(@PathVariable Integer id) {
        return sysDictService.selectById(id);
    }

    /**
     * 方法实现说明
     *
     * @param params 分页对象
     * @return 分页对象
     * @throws
     * @className: DictController
     * @methodName
     * @description: 分页查询字典信息
     * @author lxr
     * @createDate 2018-08-30 22:00
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:00
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @RequestMapping("/dictPage")
    public Page dictPage(@RequestParam Map<String, Object> params) {
        params.put(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return sysDictService.selectPage(new QueryPage<>(params), new EntityWrapper<>());
    }


    /**
     * 方法实现说明
     *
     * @param type 类型
     * @return 同类型字典
     * @throws
     * @className: DictController
     * @methodName
     * @description: 通过字典类型查找字典
     * @author lxr
     * @createDate 2018-08-30 22:01
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:01
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping("/type/{type}")
    @Cacheable(value = "dict_details", key = "#type")
    public List<SysDict> findDictByType(@PathVariable String type) {
        SysDict condition = new SysDict();
        condition.setDelFlag(CommonConstant.STATUS_NORMAL);
        condition.setType(type);
        return sysDictService.selectList(new EntityWrapper<>(condition));
    }

    /**
     * 方法实现说明
     *
     * @param sysDict 字典信息
     * @return success、false
     * @throws
     * @className: DictController
     * @methodName
     * @description: 添加字典
     * @author lxr
     * @createDate 2018-08-30 22:01
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:01
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PostMapping
    @CacheEvict(value = "dict_details", key = "#sysDict.type")
    public Result<Boolean> dict(@RequestBody SysDict sysDict) {
        return new Result<>(sysDictService.insert(sysDict));
    }

    /**
     * 方法实现说明
     *
     * @param id   ID
     * @param type 类型
     * @return success、false
     * @throws
     * @className: DictController
     * @methodName
     * @description: 删除字典，并且清除字典缓存
     * @author lxr
     * @createDate 2018-08-30 22:01
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:01
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @DeleteMapping("/{id}/{type}")
    @CacheEvict(value = "dict_details", key = "#type")
    public Result<Boolean> deleteDict(@PathVariable Integer id, @PathVariable String type) {
        return new Result<>(sysDictService.deleteById(id));
    }

    /**
     * 方法实现说明
     *
     * @param sysDict 字典信息
     * @return success/false
     * @throws
     * @className: DictController
     * @methodName
     * @description: 修改字典
     * @author lxr
     * @createDate 2018-08-30 22:02
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:02
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @PutMapping
    @CacheEvict(value = "dict_details", key = "#sysDict.type")
    public Result<Boolean> editDict(@RequestBody SysDict sysDict) {
        return new Result<>(sysDictService.updateById(sysDict));
    }
}
