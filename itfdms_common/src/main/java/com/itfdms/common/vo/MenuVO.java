package com.itfdms.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lxr
 * @Title: MenuVO
 * @ProjectName itfdms_blog
 * @Description: 菜单权限表
 * @date 2018-07-0716:19
 */

@Data
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    private  String menuName;

    /**
     * 菜单权限标识
     */
    private String permission;

    /**
     * 请求链接
     */
    private String url;

    /**
     * 请求方法
     */
    private  String method;

    /**
     * 父级菜单ID
     */
    private Integer parentId;

    /**
     * 图标
     */
    private  String icon;

    /**
     * 路径
     */
    private String path;

    /**
     * 页面
     */
    private String component;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型（0---菜单  1---按钮）
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0----正常 1----删除
     */
    private String delFlag;


    /**
     * menuId相同则相同
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof MenuVO){
            Integer targetMenuId = ((MenuVO) obj).getMenuId();
            return menuId.equals(targetMenuId);
        }
        return super.equals(obj);
    }


}
