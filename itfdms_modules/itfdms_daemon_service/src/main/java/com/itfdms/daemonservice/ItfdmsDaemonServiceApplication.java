package com.itfdms.daemonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
  *  分布式任务调度模块
  * @ProjectName:
  * @Package:        com.itfdms.daemonservice
  * @ClassName:      ItfdmsDaemonServiceApplication
  * @Description:    分布式任务调度模块
  * @Author:         lxr
  * @CreateDate:     2018-08-28 20:05
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-28 20:05
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-28
**/


@EnableDiscoveryClient
@SpringBootApplication
public class ItfdmsDaemonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsDaemonServiceApplication.class, args);
    }
}
