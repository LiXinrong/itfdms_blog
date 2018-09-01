package com.itfdms.gateway.component.filter;

import com.alibaba.fastjson.JSONObject;
import com.itfdms.common.util.Result;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.component.filter
 * @ClassName: PreviewFilter
 * @Description: 演示环境控制
 * @Author: lxr
 * @CreateDate: 2018-08-22 17:54
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 17:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@Slf4j
@RefreshScope
@Configuration
@ConditionalOnProperty(value = "security.validate.preview", havingValue = "true")
public class PreviewFilter extends ZuulFilter {

    private static final String TOKEN = "token";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        if (StrUtil.equalsIgnoreCase(request.getMethod(), Http.HttpMethod.GET.name()) || StrUtil.containsIgnoreCase(request.getRequestURI(), TOKEN)) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Result<String> result = new Result<>();
        result.setCode(479);
        result.setMsg("演示环境，没有操作权限");

        requestContext.setResponseStatusCode(479);
        requestContext.setSendZuulResponse(false);
        requestContext.getResponse().setContentType("application/json;charset=UTF-8");
        requestContext.setResponseBody(JSONObject.toJSONString(result));
        return null;
    }
}
