package com.itfdms.auth.feign.fallback;

import com.itfdms.auth.feign.UserService;
import com.itfdms.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth.feign.fallback
 * @ClassName: UserServiceFallbackImpl
 * @Description: 用户服务的fallback
 * @Author: lxr
 * @CreateDate: 2018-08-25 17:49
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 17:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Service
public class UserServiceFallbackImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 方法实现说明
     *
     * @param username 用户名
     * @return UserVO
     * @throws
     * @className: UserService
     * @methodName findUserByUsername
     * @description: 通过用户名查询用户、角色信息
     * @author lxr
     * @createDate 2018-08-25 17:58
     * @updateUser: lxr
     * @updateDate: 2018-08-25 17:58
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @Override
    public UserVO findUserByUsername(String username) {
        logger.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }

    /**
     * 方法实现说明
     *
     * @param mobile 用户名
     * @return UserVO
     * @throws
     * @className: UserService
     * @methodName findUserByMobile
     * @description: 通过手机号查询用户、角色信息
     * @author lxr
     * @createDate 2018-08-25 17:58
     * @updateUser: lxr
     * @updateDate: 2018-08-25 17:58
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @Override
    public UserVO findUserByMobile(String mobile) {
        logger.error("调用{}异常:{}", "通过手机号查询用户", mobile);
        return null;
    }

    /**
     * 方法实现说明
     *
     * @param openId 用户名
     * @return UserVO
     * @throws
     * @className: UserService
     * @methodName findUserByOpenId
     * @description: 通过OpenId查询用户、角色信息
     * @author lxr
     * @createDate 2018-08-25 17:58
     * @updateUser: lxr
     * @updateDate: 2018-08-25 17:58
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/
    @Override
    public UserVO findUserByOpenId(String openId) {
        logger.error("调用{}异常:{}", "通过OpenId查询用户", openId);
        return null;
    }
}
