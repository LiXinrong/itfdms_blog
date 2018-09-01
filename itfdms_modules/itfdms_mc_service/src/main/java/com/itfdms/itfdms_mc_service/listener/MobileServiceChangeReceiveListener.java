package com.itfdms.itfdms_mc_service.listener;

import com.itfdms.common.constant.MqQueueConstant;
import com.itfdms.common.util.mobile.MobileMsgTemplate;
import com.itfdms.itfdms_mc_service.handler.SmsMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听mobile服务状态改变发送请求
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.itfdms_mc_service.listener
 * @ClassName: MobileServiceChangeReceiveListener
 * @Description: 监听mobile服务状态改变发送请求
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:54
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
@Component
@RabbitListener(queues = MqQueueConstant.MOBILE_SERVICE_STATUS_CHANGE)
public class MobileServiceChangeReceiveListener {


    @Autowired
    private Map<String, SmsMessageHandler> messageHandlerMap;

    @RabbitHandler
    public void receive(MobileMsgTemplate mobileMsgTemplate) {
        long startTime = System.currentTimeMillis();
        log.info("消息中心接收到短信发送请求-> 手机号：{} -> 信息体：{} ", mobileMsgTemplate.getMobile(), mobileMsgTemplate.getContext());
        String channel = mobileMsgTemplate.getChannel();
        SmsMessageHandler smsMessageHandler = messageHandlerMap.get(channel);
        if (smsMessageHandler == null) {
            log.error("没有找到指定的路由通道，不进行发送处理完毕！");
            return;
        }

        smsMessageHandler.execute(mobileMsgTemplate);
        long useTime = System.currentTimeMillis() - startTime;
        log.info("调用 {} 短信网关处理完毕，耗时 {}毫秒", mobileMsgTemplate.getType(), useTime);
    }


}
