package com.itfdms.monitor.filter;

import com.alibaba.fastjson.JSONObject;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.constant.MqQueueConstant;
import com.itfdms.common.constant.enums.EnumSmsChannelTemplate;
import com.itfdms.common.util.mobile.MobileMsgTemplate;
import com.itfdms.monitor.config.MonitorPropertiesConfig;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import de.codecentric.boot.admin.event.ClientApplicationEvent;
import de.codecentric.boot.admin.event.ClientApplicationStatusChangedEvent;
import de.codecentric.boot.admin.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.monitor.filter
 * @ClassName: StatusChangeNotifier
 * @Description: 服务下线通知
 * @Author: lxr
 * @CreateDate: 2018-08-26 17:35
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-26 17:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
public class StatusChangeNotifier extends AbstractStatusChangeNotifier {

    private RabbitTemplate rabbitTemplate;
    private MonitorPropertiesConfig monitorPropertiesConfig;

    public StatusChangeNotifier(MonitorPropertiesConfig monitorPropertiesConfig, RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.monitorPropertiesConfig = monitorPropertiesConfig;
    }

    /**
     * 方法实现说明
     *
     * @param event
     * @return 事件
     * @throws Exception 异常
     * @className: StatusChangeNotifier
     * @methodName doNotify
     * @description: 消息通知逻辑
     * @author lxr
     * @createDate 2018-08-26 17:54
     * @updateUser: lxr
     * @updateDate: 2018-08-26 17:54
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @Override
    protected void doNotify(ClientApplicationEvent event) throws Exception {

        if (event instanceof ClientApplicationStatusChangedEvent) {
            log.info("Application {} ({}) is {}", event.getApplication().getName(),
                    event.getApplication().getId(), ((ClientApplicationStatusChangedEvent) event).getTo().getStatus());
            String text = String.format("应用:%s 服务ID:%s 状态改变为：%s，时间：%s"
                    , event.getApplication().getName()
                    , event.getApplication().getId()
                    , ((ClientApplicationStatusChangedEvent) event).getTo().getStatus()
                    , DateUtil.date(event.getTimestamp()).toString());

            JSONObject contextJson = new JSONObject();
            contextJson.put("name", event.getApplication().getName());
            contextJson.put("seid", event.getApplication().getId());
            contextJson.put("time", DateUtil.date(event.getTimestamp()).toString());

            //开启短信通知
            if (monitorPropertiesConfig.getMobile().getEnable()) {
                log.info("开始短信通知，内容：{}", text);
                rabbitTemplate.convertAndSend(MqQueueConstant.MOBILE_SERVICE_STATUS_CHANGE,
                        new MobileMsgTemplate(
                                CollUtil.join(monitorPropertiesConfig.getMobile().getMobiles(), ","),
                                contextJson.toJSONString(),
                                CommonConstant.ALIYUN_SMS,
                                EnumSmsChannelTemplate.SERVICE_STATUS_CHANGE.getSignName(),
                                EnumSmsChannelTemplate.SERVICE_STATUS_CHANGE.getTemplate()
                        ));
            }

            if (monitorPropertiesConfig.getDingTalk().getEnable()) {
                log.info("开始钉钉通知，内容：{}", text);
                rabbitTemplate.convertAndSend(MqQueueConstant.DINGTALK_SERVICE_STATUS_CHANGE, text);
            }


        } else {
            log.info("Application {} ({}) {}", event.getApplication().getName(),
                    event.getApplication().getId(), event.getType());
        }
    }

}
