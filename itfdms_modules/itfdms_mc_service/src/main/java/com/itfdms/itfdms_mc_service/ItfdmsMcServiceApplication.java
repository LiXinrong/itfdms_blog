package com.itfdms.itfdms_mc_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
  *  消息中心
  * @ProjectName:
  * @Package:        com.itfdms.itfdms_mc_service
  * @ClassName:      ItfdmsMcServiceApplication
  * @Description:    消息中心
  * @Author:         lxr
  * @CreateDate:     2018-08-28 20:15
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-28 20:15
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-28
**/


@EnableDiscoveryClient
@SpringBootApplication
public class ItfdmsMcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsMcServiceApplication.class, args);
    }
}
