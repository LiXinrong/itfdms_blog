package com.itfdms.common.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lxr
 * @Title: SysZuulRoute
 * @ProjectName itfdms_blog
 * @Description: 动态路由配置
 * @date 2018-07-0213:35
 */


@Data
@TableName("zuul_route")
public class SysZuulRoute extends Model<SysZuulRoute> {


    private static final long serialVersionUID = 1L;
    /**
     * router ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer serviceId;

    /**
     * 动态路由地址
     */
    private String path;


    /**
     * URL代理
     */
    private String Url;

    /**
     * 转发去掉前缀
     */
    @TableField("strip_prefix")
    private String stripPrefix;

    /**
     * 是否重试
     */
    private String retryable;

    /**
     * 是否启用
     */
    private String enabled;

    /**
     * 敏感请求头
     */
    @TableField("sensitiveHeaders_list")
    private String sensitiveheadersList;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除标识(0--正常、1--删除)
     */
    @TableField("del_flag")
    private String delFlag;

    @Override
    protected Serializable pkVal(){
        return this.serviceId;
    }

}
