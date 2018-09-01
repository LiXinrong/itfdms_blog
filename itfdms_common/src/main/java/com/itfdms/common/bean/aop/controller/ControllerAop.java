package com.itfdms.common.bean.aop.controller;

import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author lxr
 * @Title: ControllerAop
 * @ProjectName itfdms_blog
 * @Description: Controller增强
 * @date 2018-07-1422:13
 */

@Slf4j
@Aspect
@Component
public class ControllerAop {

    @Pointcut(value = "execution(public com.itfdms.common.util.Result *(..))")
    public void pointCutResult() {
    }

    /**
     * 　　* @Description: 拦截器具体实现
     * 　　* @throws
     * 　　* @author lxr
     * 　　* @date 2018-07-16 20:06
     */
    @Around("pointCutResult()")
    public Object methodRHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return methodHandler(joinPoint);
    }

    @Pointcut("execution(public com.baomidou.mybatisplus.plugins.Page *(..))")
    public void pointCutPage() {
    }


    /**
     * 　　* @Description: 拦截器具体实现
     *
     * @param joinPoint 切点 返回所有对象page
     * @return 结果包装
     * 　　* @throws
     * 　　* @author lxr
     * 　　* @date 2018-07-16 20:10
     */
    @Around("pointCutPage()")
    public Object methodPageHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return methodHandler(joinPoint);
    }


    private Object methodHandler(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) attributes.getRequest();

        String userName = request.getHeader(SecurityConstants.USER_HEADER);
        if (StringUtils.isNotBlank(userName)) {
            log.info("controller AOP get userName", userName);
        }

        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        Object result;

        result = joinPoint.proceed();
        log.info(joinPoint.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));


        if (StringUtils.isNotEmpty(userName)) {
            UserUtils.clearAllUserInfo();
        }

        return result;
    }


}
