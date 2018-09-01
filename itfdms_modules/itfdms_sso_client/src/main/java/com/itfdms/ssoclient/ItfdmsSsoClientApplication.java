package com.itfdms.ssoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


/**
  *  单点登录客户端
  * @ProjectName:
  * @Package:        com.itfdms.ssoclient
  * @ClassName:      ItfdmsSsoClientApplication
  * @Description:    单点登录客户端
  * @Author:         lxr
  * @CreateDate:     2018-08-28 18:57
  * @UpdateUser:     lxr
  * @UpdateDate:     2018-08-28 18:57
  * @UpdateRemark:   The modified content
  * @Version:        1.0
  * Copyright: Copyright (c) 2018-08-28
**/

@EnableOAuth2Sso
@SpringBootApplication
public class ItfdmsSsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItfdmsSsoClientApplication.class, args);
    }
}
