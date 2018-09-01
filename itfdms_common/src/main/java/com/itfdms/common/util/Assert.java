package com.itfdms.common.util;


import com.itfdms.common.util.exception.CheckedException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author lxr
 * @Title: Assert
 * @ProjectName itfdms_blog
 * @Description: 数据校验
 * @date 2018-07-0721:06
 */
public class Assert {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 　　* @Description:校验对象
     * 　　* @param object   待校验对象
     * 　　* @param groups    待校验的组
     * 　　* @return ${return_type}
     * 　　* @throws
     * 　　* @author lxr
     * 　　* @date 2018-07-09 19:27
     *
     */

    public static void validatorEntity(Object object, Class<?>... groups) throws CheckedException {

        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(object, groups);

        if (!constraintViolationSet.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolationSet) {
                msg.append(constraint.getMessage()).append("<br>");
            }

            throw new CheckedException(msg.toString());
        }

    }

    public static void isBlak(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CheckedException(message);
        }
    }


    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CheckedException(message);
        }
    }


}

