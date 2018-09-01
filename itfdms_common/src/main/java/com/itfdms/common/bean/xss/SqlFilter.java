package com.itfdms.common.bean.xss;

import com.itfdms.common.util.exception.CheckedException;
import org.apache.commons.lang3.StringUtils;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.common.bean.xss
 * @ClassName: SqlFilter
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-14 22:07
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-14 22:07
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/
public class SqlFilter {

    /**
      * 方法实现说明
      * @className:      SqlFilter
      * @methodName      sqlInject
      * @description:    sql注入过滤
      * @author          lxr
      * @createDate      2018-08-14 22:09
      * @updateUser:     lxr
      * @updateDate:     2018-08-14 22:09
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           str 待验证的字符串
      * @return
      * @exception
    **/

    public static String sqlInject(String str){
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new CheckedException("包含非法字符");
            }
        }

        return str;
    }



}
