package com.itfdms.common.web;

import com.itfdms.common.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author lxr
 * @Title: BaseController
 * @ProjectName itfdms_blog
 * @Description:  根据请求heard中的token获取用户角色
 * @date 2018-07-1422:07
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据请求heard中的token获取用户角色
     *
     * @return 角色名
     */
    public List<String> getRole() {
        return UserUtils.getRole(request);
    }

}
