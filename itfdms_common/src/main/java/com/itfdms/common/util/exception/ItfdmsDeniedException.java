package com.itfdms.common.util.exception;

/**
 * @author lxr
 * @Title: ItfdmsDeniedException
 * @ProjectName itfdms_blog
 * @Description: 403 授权拒绝
 * @date 2018-07-0919:50
 */
public class ItfdmsDeniedException extends RuntimeException{


    private static final long serialVersionUID = 1L;

    public ItfdmsDeniedException() {

    }

    public ItfdmsDeniedException(String message) {
        super(message);
    }

    public ItfdmsDeniedException(Throwable cause) {
        super(cause);
    }

    public ItfdmsDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItfdmsDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
