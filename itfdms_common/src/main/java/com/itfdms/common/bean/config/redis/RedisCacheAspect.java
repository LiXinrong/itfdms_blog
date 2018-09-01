package com.itfdms.common.bean.config.redis;

import com.alibaba.fastjson.JSON;
import com.itfdms.common.bean.config.redis.service.JedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lxr
 * @Title: RedisCacheAspect
 * @ProjectName itfdms_blog
 * @Description: Redis切面类
 * @date 2018-08-1317:24
 */

@Aspect
@Component
public class RedisCacheAspect {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisService jedisService;

    @Pointcut("execution(public * com.itfdms..*.*(..))")
    public void WebAspect() {
    }

    @Around("WebAspect()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        //获取类名，方法名，参数
        String redisResult = "";
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        //根据类名、方法名、参数生成key
        String key = genKey(className, methodName, args);
        logger.info("生成的key[{}]" + key);
        //得到被代理方法
        Signature signature = pjp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException();
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        //得到被代理方法上的注解
        Class methodType = method.getAnnotation(RedisCache.class).type();
        int cacheTime = method.getAnnotation(RedisCache.class).cacheTime();
        Object result = null;
        if (!jedisService.exists(key)) {
            logger.info("缓存未命中");
            //缓存不存在，则调用原方法，并将结果放入缓存中
            result = pjp.proceed(args);
            redisResult = JSON.toJSONString(result);
            jedisService.set(key, redisResult, cacheTime);
        } else {
            //缓存命中
            logger.info("缓存命中");
            redisResult = jedisService.get(key);
            //得到被代理方法的返回值类型
            Class returnType = method.getReturnType();
            result = JSON.parseObject(redisResult, returnType);
        }
        return result;
    }


    /**
     * 　　* @Description: 生成key
     * 　　* @param className 类名
     * mehtodName 方法名
     * obj  参数
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author lxr
     * 　　* @date 2018-08-13 17:41
     */

    private String genKey(String className, String mehtodName, Object[] obj) {

        StringBuffer sb = new StringBuffer("SpringBoot:");
        sb.append(className);
        sb.append("_");
        sb.append(mehtodName);
        sb.append("_");
        for (Object object : obj) {
            logger.info("obg:" + object);
            if (object != null) {
                sb.append(object + "");
                sb.append("_");
            }
        }

        return sb.toString();
    }

}
