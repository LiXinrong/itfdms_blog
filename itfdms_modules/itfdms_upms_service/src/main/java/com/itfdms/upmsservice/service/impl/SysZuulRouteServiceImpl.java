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

package com.itfdms.upmsservice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.constant.MqQueueConstant;
import com.itfdms.common.entity.SysZuulRoute;
import com.itfdms.upmsservice.mapper.SysZuulRouteMapper;
import com.itfdms.upmsservice.service.SysZuulRouteService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service.impl
 * @ClassName: SysZuulRouteServiceImpl
 * @Description: 动态路由配置表 服务实现类
 * @Author: lxr
 * @CreateDate: 2018-08-30 21:44
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 21:44
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/

@Service
public class SysZuulRouteServiceImpl extends ServiceImpl<SysZuulRouteMapper, SysZuulRoute> implements SysZuulRouteService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 方法实现说明
     *
     * @param
     * @return :
     * @throws
     * @className: SysZuulRouteServiceImpl
     * @methodName
     * @description: 立即生效配置
     * @author lxr
     * @createDate 2018-08-30 21:42
     * @updateUser: lxr
     * @updateDate: 2018-08-30 21:42
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    public Boolean applyZuulRoute() {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = selectList(wrapper);
        redisTemplate.opsForValue().set(CommonConstant.ROUTE_KEY, routeList);
        rabbitTemplate.convertAndSend(MqQueueConstant.ROUTE_CONFIG_CHANGE, 1);
        return Boolean.TRUE;
    }
}
