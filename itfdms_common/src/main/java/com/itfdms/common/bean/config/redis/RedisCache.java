package com.itfdms.common.bean.config.redis;


import java.lang.annotation.*;

/**
 * @author lxr
 * @Title: RedisCache
 * @ProjectName itfdms_blog
 * @Description: 添加该注解，代理service命中缓存则从缓存中读取数据，否则从service业务逻辑获得，并写入缓存
 * @date 2018-08-1013:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RedisCache {

    /**
     * 数据返回类型
     * @return
     */
    Class type();


    /**
     * 缓存时间
     * 默认十分钟
     * @return
     */
    int cacheTime() default 600;
}
