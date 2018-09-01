package com.itfdms.zipkinelk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;


/**
  *  java类简单作用描述
  * @ProjectName:
  * @Package:        com.itfdms.zipkinelk
  * @ClassName:      ItfdmsZipkinElkApplication
  * @Description:    链路追踪
  * @Author:         lxr
  * @CreateDate:     2018-08-26 18:05
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-26 18:05
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-26
**/

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class ItfdmsZipkinElkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsZipkinElkApplication.class, args);
    }
}
