package com.itfdms.itfdms_mc_service.handler;

import com.alibaba.fastjson.JSONObject;
import com.itfdms.common.util.mobile.DingTalkMsgTemplate;
import com.itfdms.itfdms_mc_service.config.DingTalkPropertiesConfig;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送钉钉消息逻辑
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.itfdms_mc_service.handler
 * @ClassName: DingTalkMessageHandler
 * @Description: 发送钉钉消息逻辑
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:20
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@Slf4j
@Component
public class DingTalkMessageHandler {

    @Autowired
    private DingTalkPropertiesConfig dingTalkPropertiesConfig;


    /**
     * 业务处理
     * @param text 消息
     * @return
     */
    public boolean process(String text){
        String webhook = dingTalkPropertiesConfig.getWebhook();
        if (StrUtil.isBlank(webhook)){
            log.error("钉钉配置错误，webhook为空");
            return false;
        }

        DingTalkMsgTemplate dingTalkMsgTemplate = new DingTalkMsgTemplate();
        dingTalkMsgTemplate.setMsgtype("text");
        DingTalkMsgTemplate.TextBean textBean = new DingTalkMsgTemplate.TextBean();
        textBean.setContent(text);
        dingTalkMsgTemplate.setText(textBean);
        String result = HttpUtil.post(webhook, JSONObject.toJSONString(dingTalkMsgTemplate));
        log.info("钉钉提醒成功,报文响应:{}", result);
        return true;
    }


}
