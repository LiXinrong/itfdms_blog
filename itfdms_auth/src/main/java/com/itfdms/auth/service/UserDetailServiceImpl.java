package com.itfdms.auth.service;

import com.itfdms.auth.feign.UserService;
import com.itfdms.auth.util.UserDetailsImpl;
import com.itfdms.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth.service
 * @ClassName: UserDetailServiceImpl
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-25 20:18
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 20:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = userService.findUserByUsername(username);
        return new UserDetailsImpl(userVO);
    }
}
