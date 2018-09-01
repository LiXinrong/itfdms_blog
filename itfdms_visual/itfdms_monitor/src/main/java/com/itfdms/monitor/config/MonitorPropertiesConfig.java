package com.itfdms.monitor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.monitor.config
 * @ClassName: MonitorPropertiesConfig
 * @Description: 监控配置
 * @Author: lxr
 * @CreateDate: 2018-08-26 17:41
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-26 17:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
@Configuration
@ConfigurationProperties(prefix = "notifier")
public class MonitorPropertiesConfig {

    private MonitorMobilePropertiesConfig mobile;

    private MonitorDingTalkPropertiesConfig dingTalk;

}
