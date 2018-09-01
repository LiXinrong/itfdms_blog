package com.itfdms.common.util.exception;

/**
 * @author lxr
 * @Title: UnLoginException
 * @ProjectName itfdms_blog
 * @Description: TODO
 * @date 2018-07-0919:51
 */
public class UnLoginException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnLoginException() {

    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
