package com.itfdms.common.bean.resolver;

import com.itfdms.common.constant.SecurityConstants;
import com.itfdms.common.vo.SysRole;
import com.itfdms.common.vo.UserVO;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lxr
 * @Title: TokenArgumentResolver
 * @ProjectName itfdms_blog
 * @Description: Token转换为UserVO对象
 * @date 2018-08-1322:03
 */

@Slf4j
@Configuration
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {


    /**
    　　* @Description: 入参筛选
    　　* @param methodParameter  参数集合
    　　* @return 格式化后的参数
    　　* @throws
    　　* @author lxr
    　　* @date 2018-08-13 22:04 
    　　*/
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserVO.class);
    }


   /**
     * 方法实现说明
     * @className:      TokenArgumentResolver
     * @methodName      
     * @description:    java方法作用描述
     * @author          lxr
     * @createDate      2018-08-13 23:26
     * @updateUser:     lxr
     * @updateDate:     2018-08-13 23:26
     * @updateRemark:   The modified content
     * @version         1.0
     * @see             /对类、属性、方法的说明 参考转向
     * @param methodParameter         入参集合
     * @param modelAndViewContainer   model 和 view
     * @param nativeWebRequest        web相关
     * @param webDataBinderFactory    入参解析
     * @return   包装对象
     * @exception       
   **/
   
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String username = request.getHeader(SecurityConstants.USER_HEADER);
        String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
        if (StrUtil.isBlank(username) || StrUtil.isBlank(roles)) {
            log.warn("resolveArgument error username or role is empty");
            return null;
        } else {
            log.info("resolveArgument username :{} roles:{}", username, roles);
        }
        UserVO userVO = new UserVO();
        userVO.setUserName(username);
        List<SysRole> sysRoleList = new ArrayList<>();
        Arrays.stream(roles.split(",")).forEach(role -> {
            SysRole sysRole = new SysRole();
            sysRole.setRoleName(role);
            sysRoleList.add(sysRole);
        });
        userVO.setRoleList(sysRoleList);
        return userVO;
    }
}
