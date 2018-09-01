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

import com.google.code.kaptcha.Producer;
import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.util.Assert;
import com.itfdms.common.util.Result;
import com.itfdms.upmsservice.service.SysUserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.upmsservice.controller
 * @ClassName: ValidateCodeController
 * @Description: 验证码提供
 * @Author: lxr
 * @CreateDate: 2018-08-30 22:20
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-30 22:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-30
 **/


@Controller
public class ValidateCodeController {
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService userService;

    /**
     * 方法实现说明
     *
     * @className: ValidateCodeController
     * @methodName
     * @description: 创建验证码
     * @author lxr
     * @createDate 2018-08-30 22:20
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:20
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{randomStr}")
    public void createCode(@PathVariable String randomStr, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Assert.isBlak(randomStr,"机器码不能为空");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        userService.saveImageCode(randomStr, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 方法实现说明
     *
     * @param mobile 手机号
     * @return Result
     * @throws
     * @className: ValidateCodeController
     * @methodName
     * @description: 发送手机验证码
     * 后期要加接口限制
     * @author lxr
     * @createDate 2018-08-30 22:22
     * @updateUser: lxr
     * @updateDate: 2018-08-30 22:22
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @ResponseBody
    @GetMapping(SecurityConstants.MOBILE_VALIDATE_CODE_URL_PREFIX + "/{mobile}")
    public Result<Boolean> createCode(@PathVariable String mobile) {
        Assert.isBlak(mobile,"手机号不能为空");
        return userService.sendSmsCode(mobile);
    }
}
