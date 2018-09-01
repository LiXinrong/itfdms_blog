package com.itfdms.upmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
  *  管理系统启动类
  * @ProjectName:
  * @Package:        com.itfdms.upmsservice
  * @ClassName:      ItfdmsAdminServiceApplication
  * @Description:    管理系统启动类
  * @Author:         lxr
  * @CreateDate:     2018-08-28 21:53
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-28 21:53
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-28
**/


@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.itfdms.upmsservice","com.itfdms.common.bean"})
public class ItfdmsUpmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsUpmsServiceApplication.class, args);
    }
}
