package com.itfdms.auth.component.mobile;

import com.itfdms.auth.component.mobile.MobileAuthenticationToken;
import com.itfdms.auth.feign.UserService;
import com.itfdms.auth.util.UserDetailsImpl;
import com.itfdms.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth
 * @ClassName: MobileAuthenticationProvider
 * @Description: 手机号登录校验逻辑
 * @Author: lxr
 * @CreateDate: 2018-08-25 17:36
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 17:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        UserVO userVO = userService.findUserByMobile((String) mobileAuthenticationToken.getPrincipal());

        if (userVO == null) {
            throw  new UsernameNotFoundException("手机号不存在：" + mobileAuthenticationToken.getPrincipal());
        }

        UserDetailsImpl userDetails = buildUserDetail(userVO);

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails,userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());

        return authenticationToken;
    }



    private UserDetailsImpl buildUserDetail(UserVO userVO){
        return new UserDetailsImpl(userVO);
    }


    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
