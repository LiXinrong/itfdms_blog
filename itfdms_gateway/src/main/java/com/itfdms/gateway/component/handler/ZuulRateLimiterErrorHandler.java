package com.itfdms.gateway.component.handler;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.component.handler
 * @ClassName: ZuulRateLimiterErrorHandler
 * @Description: 限流降级处理
 * @Author: lxr
 * @CreateDate: 2018-08-22 11:06
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 11:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
@Configuration
public class ZuulRateLimiterErrorHandler {

    @Bean
    public RateLimiterErrorHandler rateLimiterErrorHandler() {
        return new DefaultRateLimiterErrorHandler() {

            @Override
            public void handleSaveError(String key, Exception e) {
                log.error("保存key:[{}]异常", key, e);
            }

            @Override
            public void handleFetchError(String key, Exception e) {
                log.error("路由失败:[{}]异常", key);
            }

            @Override
            public void handleError(String msg, Exception e) {
                log.error("限流异常:[{}]", msg, e);
            }
        };
    }

}
