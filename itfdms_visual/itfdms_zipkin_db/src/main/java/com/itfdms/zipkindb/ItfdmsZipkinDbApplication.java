package com.itfdms.zipkindb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.zipkindb
  * @ClassName:      ItfdmsZipkinDbApplication
  * @Description:    zipkin mysql 存储实现
  * @Author:         lxr
  * @CreateDate:     2018-08-26 18:28
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-26 18:28
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-26
**/

@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class ItfdmsZipkinDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsZipkinDbApplication.class, args);
    }
}
