package com.itfdms.auth.feign;

import com.itfdms.auth.feign.fallback.UserServiceFallbackImpl;
import com.itfdms.common.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.auth.feign
 * @ClassName: ${CLASS_NAME}
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-25 17:39
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-25 17:39
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/


@FeignClient(name = "itfdms-upms-service",fallback = UserServiceFallbackImpl.class)
public interface UserService {


    /**
      * 方法实现说明
      * @className:      UserService
      * @methodName      findUserByUsername
      * @description:    通过用户名查询用户、角色信息
      * @author          lxr
      * @createDate      2018-08-25 17:58
      * @updateUser:     lxr
      * @updateDate:     2018-08-25 17:58
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           username 用户名
      * @return          UserVO
      * @exception
    **/
    @GetMapping("user/findUserByUsername/{username}")
    UserVO findUserByUsername(@PathVariable("username") String username);

    /**
     * 方法实现说明
     * @className:      UserService
     * @methodName      findUserByMobile
     * @description:    通过手机号查询用户、角色信息
     * @author          lxr
     * @createDate      2018-08-25 17:58
     * @updateUser:     lxr
     * @updateDate:     2018-08-25 17:58
     * @updateRemark:   The modified content
     * @version         1.0
     * @see             /对类、属性、方法的说明 参考转向
     * @param           mobile 用户名
     * @return          UserVO
     * @exception
     **/
    @GetMapping("user/findUserByMobile/{mobile}")
    UserVO findUserByMobile(@PathVariable("mobile") String mobile);

    /**
     * 方法实现说明
     * @className:      UserService
     * @methodName      findUserByOpenId
     * @description:    通过OpenId查询用户、角色信息
     * @author          lxr
     * @createDate      2018-08-25 17:58
     * @updateUser:     lxr
     * @updateDate:     2018-08-25 17:58
     * @updateRemark:   The modified content
     * @version         1.0
     * @see             /对类、属性、方法的说明 参考转向
     * @param           openId 用户名
     * @return          UserVO
     * @exception
     **/
    @GetMapping("user/findUserByOpenId/{openId}")
    UserVO findUserByOpenId(@PathVariable("openId") String openId);

}
