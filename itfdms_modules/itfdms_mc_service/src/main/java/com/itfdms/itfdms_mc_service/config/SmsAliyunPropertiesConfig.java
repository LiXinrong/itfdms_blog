package com.itfdms.itfdms_mc_service.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 阿里大鱼短息服务配置
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.itfdms_mc_service.config
 * @ClassName: SmsAliyunPropertiesConfig
 * @Description: 阿里大鱼短息服务配置
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:17
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
@Configuration
@ConditionalOnExpression("!'${sms.aliyun}'.isEmpty()")
@ConfigurationProperties(prefix = "sms.aliyun")
public class SmsAliyunPropertiesConfig {

    /**
     * 应用key
     */
    private String accessKey;

    /**
     * 应用密钥
     */
    private String secretKey;

    /**
     * 短信模板配置
     */
    private Map<String, String> channels;

}
