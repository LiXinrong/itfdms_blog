package com.itfdms.gateway.service;

import com.netflix.zuul.context.RequestContext;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.service
 * @ClassName: LogSendService
 * @Description: 消息通道接口
 * @Author: lxr
 * @CreateDate: 2018-08-22 12:50
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 12:50
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public interface LogSendService {

    /**
     * 往消息通道发送消息
     * @param requestContext
     */
    void send(RequestContext requestContext);

}
