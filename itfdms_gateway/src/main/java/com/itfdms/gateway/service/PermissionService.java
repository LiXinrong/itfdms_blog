package com.itfdms.gateway.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.service
 * @ClassName: PermissionService
 * @Description: 权限接口
 * @Author: lxr
 * @CreateDate: 2018-08-22 12:52
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 12:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public interface PermissionService {

    /**
     * 判断请求是否有权限
     * @param request
     * @param authentication 认证信息
     * @return  是否有权限
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
