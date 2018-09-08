package com.itfdms.auth.config;

import com.itfdms.auth.component.mobile.MobileSecurityConfigurer;
import com.itfdms.common.bean.config.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth.config
 * @ClassName: ItfdmsSecurityConfigurerAdapter
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-22 21:48
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 21:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
@Configuration
@EnableWebSecurity
public class ItfdmsSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Autowired
    private MobileSecurityConfigurer mobileSecurityConfigurer;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.formLogin().loginPage("/authentication/require")
                        .loginProcessingUrl("/authentication/form")
                        .and()
                        .authorizeRequests();
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
        http.apply(mobileSecurityConfigurer);
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
