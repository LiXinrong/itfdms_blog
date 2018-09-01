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

package com.itfdms.upmsservice.service;

import com.baomidou.mybatisplus.service.IService;
import com.itfdms.common.entity.SysZuulRoute;

/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.service
 * @ClassName: SysZuulRouteService
 * @Description: 动态路由配置表 服务类
 * @Author: lxr
 * @CreateDate: 2018-08-30 18:48
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 18:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/
public interface SysZuulRouteService extends IService<SysZuulRoute> {

    /**
     * 方法实现说明
     *
     * @param : null
     * @return : null
     * @throws
     * @className: SysZuulRouteService
     * @methodName
     * @description: 立即生效配置
     * @author lxr
     * @createDate 2018-08-30 18:48
     * @updateUser: lxr
     * @updateDate: 2018-08-30 18:48
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    Boolean applyZuulRoute();
}
