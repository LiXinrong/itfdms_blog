package com.itfdms.gateway.service.impl;

import com.itfdms.common.vo.MenuVO;
import com.itfdms.gateway.feign.MenuService;
import com.itfdms.gateway.service.PermissionService;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.service.fallback
 * @ClassName: PermissionServiceImpl
 * @Description: 权限验证工具类
 * @Author: lxr
 * @CreateDate: 2018-08-22 13:17
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 13:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                log.warn("角色列表为空：{}", authentication.getPrincipal());
                return hasPermission;
            }

            Set<MenuVO> urls = new HashSet<>();
            for (SimpleGrantedAuthority authority : grantedAuthorityList) {
                if (!StrUtil.equals(authority.getAuthority(), "ROLE_USER")) {
                    Set<MenuVO> menuVOSet = menuService.findMenuByRole(authority.getAuthority());
                    if (CollUtil.isNotEmpty(menuVOSet)) {
                        CollUtil.addAll(urls, menuVOSet);
                    }
                }
            }

            for (MenuVO menu : urls) {
                if (StringUtils.isNotEmpty(menu.getUrl()) && antPathMatcher.match(menu.getUrl(), request.getRequestURI()) && request.getMethod().equalsIgnoreCase(menu.getMethod())) {
                    hasPermission = true;
                    break;
                }
            }

        }
        return hasPermission;
    }

}
