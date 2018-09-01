package com.itfdms.gateway.component.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.Result;
import com.itfdms.common.util.exception.ItfdmsDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.component.handler
 * @ClassName: ItfdmsAccessDeniedHandler
 * @Description: 授权拒绝处理器，覆盖默认OAuth2AccessDeniedHandler
 * 包装失败信息ItfdmsDeniedException
 * @Author: lxr
 * @CreateDate: 2018-08-22 10:52
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-22 10:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
@Component
public class ItfdmsAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 方法实现说明
     *
     * @param request
     * @param response
     * @param authDeniedException
     * @return
     * @throws
     * @className: ItfdmsAccessDeniedHandler
     * @methodName
     * @description: 拒绝授权处理 采用Result包装     * @author lxr
     * @createDate 2018-08-22 10:57
     * @updateUser: lxr
     * @updateDate: 2018-08-22 10:57
     * @updateRemark: The modified content
     * @version 1.0
     * @see /对类、属性、方法的说明 参考转向
     **/

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authDeniedException) throws IOException, ServletException {

        log.info("授权失败，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding(CommonConstant.UTF8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        Result<String> result = new Result<>(new ItfdmsDeniedException("授权失败，拒绝访问。"));
        response.setStatus(HttpStatus.SC_FORBIDDEN);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString(result));
    }

}
