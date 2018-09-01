package com.itfdms.itfdms_mc_service.handler;

import com.itfdms.common.util.mobile.MobileMsgTemplate;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.itfdms_mc_service.handler
 * @ClassName: SmsMessageHandler
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:38
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


public interface SmsMessageHandler {
    /**
     * 执行入口
     *
     * @param mobileMsgTemplate 信息
     */
    void execute(MobileMsgTemplate mobileMsgTemplate);

    /**
     * 数据校验
     *
     * @param mobileMsgTemplate 信息
     */
    void check(MobileMsgTemplate mobileMsgTemplate);

    /**
     * 业务处理
     *
     * @param mobileMsgTemplate 信息
     * @return boolean
     */
    boolean process(MobileMsgTemplate mobileMsgTemplate);

    /**
     * 失败处理
     *
     * @param mobileMsgTemplate 信息
     */
    void fail(MobileMsgTemplate mobileMsgTemplate);
}
