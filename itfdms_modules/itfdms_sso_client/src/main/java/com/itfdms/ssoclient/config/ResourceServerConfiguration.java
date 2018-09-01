package com.itfdms.ssoclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 当前配置 暴露监控信息
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.ssoclient.config
 * @ClassName: ResourceServerConfiguration
 * @Description: 当前配置 暴露监控信息
 * @Author: lxr
 * @CreateDate: 2018-08-28 19:53
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 19:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
    }

}
