package com.itfdms.monitor.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.monitor.config
 * @ClassName: MonitorDingTalkPropertiesConfig
 * @Description: DingDing通知
 * @Author: lxr
 * @CreateDate: 2018-08-26 17:37
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-26 17:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
@ConditionalOnExpression("!'${webhook}'.isEmpty()")
public class MonitorDingTalkPropertiesConfig {

    /**
     * 是否开启网页钉钉通知
     */
    private Boolean enable;
}
