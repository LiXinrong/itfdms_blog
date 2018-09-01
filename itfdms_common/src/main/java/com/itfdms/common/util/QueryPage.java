package com.itfdms.common.util;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author lxr
 * @Title: QueryPage
 * @ProjectName itfdms_blog
 * @Description: 分页插件
 * @date 2018-07-1119:36
 */
public class QueryPage<T> extends Page<T> {

    private static final String PAGE = "page";
    private static final String LIMIT = "limit";
    private static final String ORDER_BY_FIELD = "orderByField";
    private static final String IS_ASC = "isAsc";

    public QueryPage(Map<String, Object> parmms) {
        super(Integer.parseInt(parmms.getOrDefault(PAGE, 1).toString()), Integer.parseInt(parmms.getOrDefault(LIMIT, 20).toString()));

        String orderByField = parmms.getOrDefault(ORDER_BY_FIELD,"").toString();
        if (StringUtils.isNotEmpty(orderByField)) {
            this.setOrderByField(orderByField);
        }

        Boolean isAsc = Boolean.parseBoolean(parmms.getOrDefault(IS_ASC,Boolean.TRUE).toString());
        this.setAsc(isAsc);

        parmms.remove(PAGE);
        parmms.remove(LIMIT);
        parmms.remove(ORDER_BY_FIELD);
        parmms.remove(IS_ASC);

        this.setCondition(parmms);
    }

}
