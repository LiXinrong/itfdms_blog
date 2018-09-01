package com.itfdms.common.util;

import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.exception.CheckedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.codec.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author lxr
 * @Title: AuthUtils
 * @ProjectName itfdms_blog
 * @Description: 认证授权相关工具类
 * @date 2018-07-1116:32
 */

@Slf4j
public class AuthUtils {

    private static final String BASIC_ = "Basic";


    /**
     * 　　* @Description: 从header 请求中的clientId/clientsecect
     * 　　* @param header header中的参数
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author lxr
     * 　　* @date 2018-07-11 16:34
     */

    public static String[] extractAndDecodeHeader(String header) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;

        try {
            decoded = Base64.decode(base64Token);
        } catch (Exception e) {
            throw new CheckedException("Failed to decode basic authentication token");
        }

        String token = new String(decoded,CommonConstant.UTF8);

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new CheckedException("Invalid basic authentication token");
        }

        return new String[] {token.substring(0,delim),token.substring(delim + 1)};

    }

    /**
     * 从header请求中的clientId/clientsecect
     * @param request
     * @return
     */
    public static String[] extractAndDecodeHeader(HttpServletRequest request) throws IOException{

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith(BASIC_)) {
            throw new CheckedException("请求头中client信息为空");
        }

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;

        try {
            decoded = Base64.decode(base64Token);
        } catch (Exception e) {
            throw new CheckedException("Failed to decode basic authentication token");
        }

        String token = new String(decoded,CommonConstant.UTF8);
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new CheckedException("Invalid basic authentication token");
        }
        return new String[] {token.substring(0,delim),token.substring(delim + 1)};
    }

}
