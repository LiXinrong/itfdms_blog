package com.itfdms.gateway.component.config;


import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.entity.SysZuulRoute;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * java类简单作用描述
 *
 * @ProjectName: itfdms_blog
 * @Package: com.itfdms.gateway.component.config
 * @ClassName: ${CLASS_NAME}
 * @Description: java类作用描述
 * @Author: lxr
 * @CreateDate: 2018-08-16 21:49
 * @UpdateUser: lxr
 * @UpdateDate: 2018-08-16 21:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 **/

@Slf4j
public class DynamicRouteLocator extends DiscoveryClientRouteLocator {

    private ZuulProperties zuulProperties;
    private RedisTemplate redisTemplate;

    public DynamicRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties, ServiceInstance localServiceInstance,RedisTemplate redisTemplate) {
        super(servletPath, discovery, properties, localServiceInstance);
        this.zuulProperties = properties;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 重写路由配置
     * <p>
     * 1. properties 配置。
     * 2. eureka 默认配置。
     * 3. DB数据库配置。
     *
     * @return 路由表
     */
    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //读取properties配置、eureka默认配置
        routesMap.putAll(super.locateRoutes());
        log.debug("初始默认的路由配置完成");
        routesMap.putAll(locateRoutesFromDb());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StrUtil.isNotBlank(this.zuulProperties.getPrefix())) {
                path = this.zuulProperties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return routesMap;
    }


    /**
      * 方法实现说明
      * @className:      DynamicRouteLocator
      * @methodName      locateRoutesFromDb
      * @description:    Redis中保存的，没有从upms拉去，避免启动链路依赖问题（取舍），网关依赖业务模块的问题
      * @author          lxr
      * @createDate      2018-08-21 9:36
      * @updateUser:     lxr
      * @updateDate:     2018-08-21 9:36
      * @updateRemark:   The modified content
      * @version         1.0
      * @see             /对类、属性、方法的说明 参考转向
      * @param           
      * @return          
      * @exception       
    **/
    
    private Map<String,ZuulProperties.ZuulRoute> locateRoutesFromDb(){
        Map<String,ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();

        Object obj = redisTemplate.opsForValue().get(CommonConstant.ROUTE_KEY);
        if (obj == null) {
            return routes;
        }

        List<SysZuulRoute> results = (List<SysZuulRoute>) obj;
        for (SysZuulRoute result : results) {
            if (StrUtil.isBlank(result.getPath()) && StrUtil.isBlank(result.getUrl())){
                continue;
            }

            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                zuulRoute.setId(result.getServiceId().toString());
                zuulRoute.setPath(result.getPath());
                zuulRoute.setServiceId(result.getServiceId().toString());
                zuulRoute.setRetryable(StrUtil.equals(result.getRetryable(),"0") ? Boolean.FALSE : Boolean.TRUE);
                zuulRoute.setStripPrefix(StrUtil.equals(result.getStripPrefix(),"0") ? Boolean.FALSE : Boolean.TRUE);
                zuulRoute.setUrl(result.getUrl());
                List<String> sensitiveHeadersList = StrUtil.splitTrim(result.getSensitiveheadersList(),",");
                if (sensitiveHeadersList != null) {
                    Set<String> sensitiveHeaderSet = CollUtil.newHashSet();
                    sensitiveHeadersList.forEach(sensitiveHeader -> sensitiveHeaderSet.add(sensitiveHeader));
                    zuulRoute.setSensitiveHeaders(sensitiveHeaderSet);
                    zuulRoute.setCustomSensitiveHeaders(true);
                }
            } catch (Exception e) {
                log.error("从数据库加载路由配置异常", e);
            }
            log.debug("添加数据库自定义的路由配置,path：{}，serviceId:{}", zuulRoute.getPath(), zuulRoute.getServiceId());
            routes.put(zuulRoute.getPath(),zuulRoute);
        }
        return routes;
    }


}
