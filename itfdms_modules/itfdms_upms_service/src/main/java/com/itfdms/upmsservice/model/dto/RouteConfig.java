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

package com.itfdms.upmsservice.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;


/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice.model.dto
  * @ClassName:      RouteConfig
  * @Description:    java类作用描述
  * @Author:         lxr
  * @CreateDate:     2018-08-30 17:33
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-30 17:33
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-30
**/

@Data
public class RouteConfig implements Serializable{

    @com.alibaba.fastjson.annotation.JSONField(name = "path")
    private String path;
    @com.alibaba.fastjson.annotation.JSONField(name = "component")
    private String component;
    @com.alibaba.fastjson.annotation.JSONField(name = "name")
    private String name;
    @com.alibaba.fastjson.annotation.JSONField(name = "components")
    private String components;
    @com.alibaba.fastjson.annotation.JSONField(name = "redirect")
    private String redirect;
    @com.alibaba.fastjson.annotation.JSONField(name = "props")
    private String props;
    @com.alibaba.fastjson.annotation.JSONField(name = "alias")
    private String alias;
    @com.alibaba.fastjson.annotation.JSONField(name = "children")
    private String children;
    @com.alibaba.fastjson.annotation.JSONField(name = "beforeEnter")
    private String beforeEnter;
    @com.alibaba.fastjson.annotation.JSONField(name = "meta")
    private Map<String,String> meta;
    @com.alibaba.fastjson.annotation.JSONField(name = "caseSensitive")
    private Boolean caseSensitive;
    @com.alibaba.fastjson.annotation.JSONField(name = "pathToRegexpOptions")
    private String pathToRegexpOptions;
}
