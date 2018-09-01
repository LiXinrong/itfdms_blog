package com.itfdms.common.util.exception;

/**
 * @author lxr
 * @Title: Exctption
 * @ProjectName itfdms_blog
 * @Description: 对象比较异常
 * @date 2018-07-0919:42
 */

public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckedException() {

    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
