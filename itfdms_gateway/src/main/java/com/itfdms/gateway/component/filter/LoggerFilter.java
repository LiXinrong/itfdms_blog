package com.itfdms.gateway.component.filter;

import com.itfdms.gateway.service.LogSendService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.component.filter
 * @ClassName: LoggerFilter
 * @Description: 网关日志统一拦截
 * @Author: lxr
 * @CreateDate: 2018-08-22 17:51
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 17:51
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    private LogSendService logSendService;

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logSendService.send(RequestContext.getCurrentContext());
        return null;
    }
}
