package com.itfdms.common.vo;

import com.itfdms.common.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxr
 * @Title: LogVO
 * @ProjectName itfdms_blog
 * @Description: TODO
 * @date 2018-07-0716:17
 */

@Data
public class LogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private SysLog sysLog;
    private String userName;

}
