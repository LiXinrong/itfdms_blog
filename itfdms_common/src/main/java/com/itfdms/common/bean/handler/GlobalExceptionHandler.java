package com.itfdms.common.bean.handler;

import com.itfdms.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lxr
 * @Title: GlobalExceptionHandler
 * @ProjectName itfdms_blog
 * @Description: 全局异常拦截器
 * @date 2018-08-1318:01
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result exception(Exception e) {
        log.info("保存全局异常信息 ex={}" + e.getMessage(), e);
        return new Result<>(e);
    }

}
