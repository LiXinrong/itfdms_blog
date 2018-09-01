package com.itfdms.ssoclient.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制类
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.ssoclient.controller
 * @ClassName: UserController
 * @Description: 用户控制类
 * @Author: lxr
 * @CreateDate: 2018-08-28 19:56
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-28 19:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@RestController
public class UserController {

    @GetMapping("/")
    public Authentication user(Authentication authentication) {
        return authentication;
    }

}
