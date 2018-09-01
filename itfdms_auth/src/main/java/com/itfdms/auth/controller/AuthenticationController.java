package com.itfdms.auth.controller;

import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth.controller
 * @ClassName: AuthenticationController
 * @Description: 权限验证控制器
 * @Author: lxr
 * @CreateDate: 2018-08-25 20:20
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 20:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@Controller
@RequestMapping("authentication")
public class AuthenticationController {


    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 认证页面
     *
     * @return
     */
    @GetMapping("/require")
    private ModelAndView require() {
        return new ModelAndView("ftl/login");
    }

    /**
     * 方法实现说明
     * 用户信息校验
     *
     * @param authentication 信息
     * @return 用户信息
     * @throws
     * @className: AuthenticationController
     * @methodName
     * @description: 用户信息校验
     * @author lxr
     * @createDate 2018-08-25 20:24
     * @updateUser: lxr
     * @updateDate: 2018-08-25 20:24
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    @RequestMapping("user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }


    /**
     * 清除Redis中 accesstoken refreshtoken
     *
     * @param accesstoken accesstoken
     * @return true/false
     */
    @PostMapping("/removeToken")
    @CacheEvict(value = SecurityConstants.TOKEN_USER_DETAIL, key = "#accesstoken")
    public Result<Boolean> removeToken(String accesstoken) {
        return new Result<>(consumerTokenServices.revokeToken(accesstoken));
    }


}
