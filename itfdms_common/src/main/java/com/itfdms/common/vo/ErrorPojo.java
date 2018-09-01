package com.itfdms.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxr
 * @Title: ErrorPojo
 * @ProjectName itfdms_blog
 * @Description: SpringBoot 异常对象
 * @date 2018-07-0715:52
 */

@Data
public class ErrorPojo implements Serializable {

    private static final long serialVersionUID = -6462516256548396934L;

    @JSONField(name = "timestamp")
    private Long timestamp;
    @JSONField(name = "status")
    private int status;
    @JSONField(name = "error")
    private String error;
    @JSONField(name = "exception")
    private String exception;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "path")
    private String path;

}
