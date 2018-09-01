package com.itfdms.common.util;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.itfdms.common.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

/**
 * @author lxr
 * @Title: UserUtils
 * @ProjectName itfdms_blog
 * @Description: 用户相关工具类
 * @date 2018-07-1421:45
 */

@Slf4j
public class UserUtils {

    private static final ThreadLocal<String> THREAD_LOCAL_USER = new TransmittableThreadLocal<>();
    private static final String KEY_USER = "user";

    /**
    　　* @Description: 根据请求头header中的token获取角色列表
    　　* @param ${tags} 
    　　* @return ${return_type} 
    　　* @throws
    　　* @author lxr
    　　* @date 2018-07-14 21:59 
    　　*/
    
    public static List<String> getRole(HttpServletRequest request){
        String token = getToken(request);
        String key = Base64.getEncoder().encodeToString(CommonConstant.SIGN_KEY.getBytes());
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        List<String> roleNames = (List<String>) claims.get("authorization");
        return roleNames;
    }

    /**
    　　* @Description: 获取请求头中的token
    　　* @param request
    　　* @return token
    　　* @throws
    　　* @author lxr
    　　* @date 2018-07-14 21:52
    　　*/

    public static String getToken(HttpServletRequest request){
        String authorization = request.getHeader(CommonConstant.REQ_HEADER);
        return StringUtils.substringAfter(authorization,CommonConstant.TOKEN_SPLIT);
    }

    /**
    　　* @Description: 设置用户信息
    　　* @param ${tags}
    　　* @return ${return_type}
    　　* @throws
    　　* @author lxr
    　　* @date 2018-07-14 22:01
    　　*/

    public static void setUser(String userName){
        THREAD_LOCAL_USER.set(userName);
        MDC.put(KEY_USER,userName);
    }

    /**
    　　* @Description: 获取用户信息
    　　* @param ${tags}
    　　* @return ${return_type}
    　　* @throws
    　　* @author lxr
    　　* @date 2018-07-14 22:04
    　　*/

    public static String getUser(){
        return THREAD_LOCAL_USER.get();
    }

    /**
     * 清除用户信息
     */
    public static void clearAllUserInfo(){
        THREAD_LOCAL_USER.remove();
        MDC.remove(KEY_USER);
    }
}
