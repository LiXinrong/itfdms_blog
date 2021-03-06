package com.itfdms.common.constant.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lxr
 * @Title: EnumSmsChannelTemplate
 * @ProjectName itfdms_blog
 * @Description: 短信通道模板
 * @date 2018-07-1119:32
 */
public enum EnumSmsChannelTemplate {

    /**
     * 登录验证
     */
    LOGIN_NAME_LOGIN("loginCodeChannel", "登录验证"),
    /**
     * 服务异常提醒
     */
    SERVICE_STATUS_CHANGE("serviceStatusChange", "系统服务异常");


    /**
     * 模板名称
     */
    @Getter
    @Setter
    private String template;

    /**
     * 模板签名
     */
    @Getter
    @Setter
    private String signName;


    EnumSmsChannelTemplate(String template, String signName) {
        this.template = template;
        this.signName =signName;
    }
}
