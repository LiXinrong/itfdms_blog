package com.itfdms.common.bean.interceptor;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author lxr
 * @Title: DataScope
 * @ProjectName itfdms_blog
 * @Description: 数据权限  增强查询参数
 * @date 2018-08-1318:15
 */

@Data
public class DataScope extends HashMap {

    /**
     * 限制范围的字段名称
     */
    private String scopeName = "dept_id";

    /**
     * 具体的数据范围
     */
    private List<Integer> deptIds;

    /**
     * 是否只查询本部门
     */
    private Boolean isOnly = false;

}
