package com.itfdms.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * java类简单作用描述
 *
 * @ProjectName:
 * @Package: com.itfdms.auth
 * @ClassName: ItfdmsAuthApplication
 * @Description: 获取用户信息也是通过这个应用实现
 * 这里既是认证服务器，也是资源服务器
 * @Author: lxr
 * @CreateDate: 2018-08-25 20:16
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 20:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * Copyright: Copyright (c) 2018-08-25
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.itfdms.auth", "com.itfdms.common.bean"})
public class ItfdmsAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItfdmsAuthApplication.class, args);
    }

}
