package com.itfdms.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lxr
 * @Title: UserVO
 * @ProjectName itfdms_blog
 * @Description: 用户表
 * @date 2018-07-0716:33
 */

@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 随即盐
     */
    private String salt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0----正常 1----删除
     */
    private String delFlag;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 简述
     */
    private String description;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色列表
     */
    private List<SysRole> roleList;

}
