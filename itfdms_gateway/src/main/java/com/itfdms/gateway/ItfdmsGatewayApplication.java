package com.itfdms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableZuulProxy
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.itfdms.gateway", "com.itfdms.common.bean"})
@SpringBootApplication
public class ItfdmsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsGatewayApplication.class, args);
    }


}
