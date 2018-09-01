package com.itfdms.itfdms_mc_service.handler;

import com.itfdms.common.util.mobile.MobileMsgTemplate;

/**
 * 抽象hander
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.itfdms_mc_service.handler
 * @ClassName: AbstractMessageHandler
 * @Description: 抽象hander
 * @Author: lxr
 * @CreateDate: 2018-08-28 20:38
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 20:38
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public abstract class AbstractMessageHandler implements SmsMessageHandler{

    /**
     * 执行入口
     *
     * @param mobileMsgTemplate 信息
     */
    @Override
    public void execute(MobileMsgTemplate mobileMsgTemplate) {
        check(mobileMsgTemplate);
        if (!process(mobileMsgTemplate)){
            fail(mobileMsgTemplate);
        }
    }

    /**
     * 数据校验
     *
     * @param mobileMsgTemplate 信息
     */
    @Override
    public abstract void check(MobileMsgTemplate mobileMsgTemplate);

    /**
     * 业务处理
     *
     * @param mobileMsgTemplate 信息
     * @return boolean
     */
    @Override
    public abstract boolean process(MobileMsgTemplate mobileMsgTemplate);

    /**
     * 失败处理
     *
     * @param mobileMsgTemplate 信息
     */
    @Override
    public abstract void fail(MobileMsgTemplate mobileMsgTemplate);

}
