package com.itfdms.common.constant;

/**
 * @author lxr
 * @Title: CommonConstant
 * @ProjectName itfdms_blog
 * @Description: TODO
 * @date 2018-07-1116:49
 */
public interface CommonConstant {

    /**
     * token请求头名称
     */
    String REQ_HEADER = "Authorization";

    /**
     * Token分隔符
     */
    String TOKEN_SPLIT = "Bearer";

    /**
     * jwt签名
      */
    String SIGN_KEY =   "itfdms";

    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 按钮
     */
    String BUTTON = "1";

    /**
     * 删除标记
     */
    String DEL_FLAG = "del_flag";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * json资源
     */
    String CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 阿里大鱼
     */
    String ALIYUN_SMS = "aliyun_sms";

    /**
     * 路由信息  Redis保存的key
     */
    String ROUTE_KEY = "route_key";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;
}
