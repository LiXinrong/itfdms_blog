package com.itfdms.gateway.component.filter;

import com.itfdms.common.bean.config.FilterIgnorePropertiesConfig;
import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.util.AuthUtils;
import com.itfdms.common.util.exception.ValidateCodeException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.component.filter
 * @ClassName: ValidateCodeFilter
 * @Description: 验证码拦截器操作类
 * @Author: lxr
 * @CreateDate: 2018-08-22 18:03
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 18:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
@RefreshScope
@Configuration("validateCodeFilter")
@ConditionalOnProperty(value = "security.validate.code",havingValue = "true")
public class ValidateCodeFilter extends ZuulFilter {

    private static final String EXPIRED_CAPTCHA_ERROR = "验证码已过期，请重新获取";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        if (!StrUtil.containsAnyIgnoreCase(request.getRequestURI(), SecurityConstants.OAUTH_TOKEN_URL,SecurityConstants.MOBILE_TOKEN_URL)){
            return  false;
        }

        try {
            String[] clientInfos = AuthUtils.extractAndDecodeHeader(request);
            if (CollUtil.containsAny(filterIgnorePropertiesConfig.getClients(), Arrays.asList(clientInfos))){
                return false;
            }
        } catch (IOException e) {
            log.error("解析终端信息失败", e);
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }


    /**
      * 方法实现说明
      * @className:      ValidateCodeFilter
      * @methodName      checkCode
      * @description:    code校验
      * @author          lxr
      * @createDate      2018-08-22 18:15
      * @updateUser:     lxr
      * @updateDate:     2018-08-22 18:15
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           request
      * @return
      * @exception       ValidateCodeException 验证码校验异常
    **/

    private void checkCode(HttpServletRequest request) throws ValidateCodeException {
        String code = request.getParameter("code");
        if (StrUtil.isBlank(code)) {
            throw new ValidateCodeException("请输入验证码");
        }

        String randomStr = request.getParameter("randomStr");
        if (StrUtil.isBlank(randomStr)) {
            randomStr = request.getParameter("mobile");
        }

        String key = SecurityConstants.DEFAULT_CODE_KEY + randomStr;
        if (!redisTemplate.hasKey(key)) {
            throw new ValidateCodeException(EXPIRED_CAPTCHA_ERROR);
        }

        Object codeObj = redisTemplate.opsForValue().get(key);
        if (codeObj == null) {
            throw new ValidateCodeException(EXPIRED_CAPTCHA_ERROR);
        }

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisTemplate.delete(key);
            throw new ValidateCodeException(EXPIRED_CAPTCHA_ERROR);
        }

        if (!StrUtil.equals(saveCode, code)) {
            redisTemplate.delete(key);
            throw new ValidateCodeException("验证码错误，请重新输入");
        }
        redisTemplate.delete(key);
    }

}
