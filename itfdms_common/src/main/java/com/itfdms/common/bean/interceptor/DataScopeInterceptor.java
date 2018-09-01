package com.itfdms.common.bean.interceptor;

import com.baomidou.mybatisplus.plugins.SqlParserHandler;
import com.baomidou.mybatisplus.toolkit.PluginUtils;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author lxr
 * @Title: DataScopeInterceptor
 * @ProjectName itfdms_blog
 * @Description: 数据权限插件 guns
 * @date 2018-08-1318:19
 */

@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor extends SqlParserHandler implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        //判断是否为SELECT语句
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())){
            return invocation.proceed();
        }

        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();

        //查找参数中包含DataScope类型的参数
        DataScope dataScope = findDataScopeObject(parameterObject);
        if (dataScope == null) {
            return invocation.proceed();
        }else {
            String scopeName = dataScope.getScopeName();
            List<Integer> deptIds = dataScope.getDeptIds();
            if (StrUtil.isNotBlank(scopeName) && CollectionUtil.isNotEmpty(deptIds)){
                String join = CollectionUtil.join(deptIds, ",");
                originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
                metaObject.setValue("delegate.boundSql.sql", originalSql);
            }
            return invocation.proceed();
        }

    }
    
    /**
    　　* @Description: 生成拦截对象代理
    　　* @throws
        * @Param target 目标对象
    　　* @author lxr
    　　* @date 2018-08-13 21:58
        * @return 代理对象
    　　*/
    
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler){
            return Plugin.wrap(target,this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
    　　* @Description: 查找参数是否包含DataScope对象
    　　* @throws
        * @Param parameterObject  参数列表
    　　* @author lxr
    　　* @date 2018-08-13 21:19 
    　　*/
    
    private DataScope findDataScopeObject(Object parameterObject){
        if (parameterObject instanceof DataScope) {
            return (DataScope) parameterObject;
        }else if(parameterObject instanceof Map){
            for (Object val :  ((Map<?,?>)parameterObject).values()) {
                if (val instanceof DataScope){
                    return (DataScope) val;
                }
            }
        }
        return null;
    }


}
