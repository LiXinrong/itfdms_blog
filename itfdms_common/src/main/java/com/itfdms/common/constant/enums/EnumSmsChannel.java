package com.itfdms.common.constant.enums;

/**
 * @author lxr
 * @Title: EnumSmsChannel
 * @ProjectName itfdms_blog
 * @Description: 短信通道枚举类
 * @date 2018-07-1116:50
 */


public enum EnumSmsChannel {

    /**
     * 阿里大鱼短信通道
     */
    ALIYUN("ALIYUN_SMS","阿里大鱼");

    /**
     * 通道名称
     */
    private String name;

    /**
     * 通道描述
     */
    private String description;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    EnumSmsChannel(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
