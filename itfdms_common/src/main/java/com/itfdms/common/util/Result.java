package com.itfdms.common.util;

import java.io.Serializable;

/**
 * @author lxr
 * @Title: Result
 * @ProjectName itfdms_blog
 * @Description: 响应信息主体
 * @date 2018-07-1421:35
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1676978629387368094L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public Result(Throwable throwable){
        super();
        this.msg = getMsg();
        this.code = FAIL;
    }

}
