package com.itfdms.auth.config;

import lombok.Data;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth.config
 * @ClassName: SocialPropertiesConfig
 * @Description: 登录基础配置
 * @Author: lxr
 * @CreateDate: 2018-08-25 20:04
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 20:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Data
public class SocialPropertiesConfig {

    /**
     * 提供商
     */
    private String providerId;
    /**
     * 应用ID
     */
    private String clientId;
    /**
     * 应用密钥
     */
    private String clientSecret;

}
