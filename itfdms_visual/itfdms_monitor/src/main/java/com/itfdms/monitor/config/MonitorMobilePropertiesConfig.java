package com.itfdms.monitor.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.monitor.config
 * @ClassName: MonitorMobilePropertiesConfig
 * @Description: DingDing手机通知
 * @Author: lxr
 * @CreateDate: 2018-08-26 17:39
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-26 17:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
@ConditionalOnExpression("!'${mobiles}'.isEmpty()")
public class MonitorMobilePropertiesConfig {

    /**
     * 是否开启手机钉钉通知
     */
    private Boolean enable;

    /**
     * 钉钉通知手机号集合
     */
    private List<String> mobiles = new ArrayList<>();

}
