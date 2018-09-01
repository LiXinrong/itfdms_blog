package com.itfdms.itfdms_mc_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 钉钉服务配置
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.itfdms_mc_service.config
 * @ClassName: DingTalkPropertiesConfig
 * @Description: 钉钉服务配置
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:16
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
@Configuration
@ConfigurationProperties(prefix = "sms.dingtalk")
public class DingTalkPropertiesConfig {

    /**
     * webhook
     */
    private String webhook;

}
