package com.itfdms.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lxr
 * @Title: SysRole
 * @ProjectName itfdms_blog
 * @Description: 角色表
 * @date 2018-07-0716:31
 */

@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;
    private String delFlag;

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
