package com.itfdms.common.bean.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxr
 * @Title: RedisConfigrationProperties
 * @ProjectName itfdms_blog
 * @Description: TODO
 * @date 2018-08-1014:23
 */

@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisConfigrationProperties {

    //集群节点
    private List<String> nodes = new ArrayList<>();
    //密码
    @Value("${spring.redis.password}")
    private String password;

    private Integer maxRedirects;

    //超时时间
    @Value("${spring.redis.timeout}")
    private String timeout;

    //以下为连接池配置
    @Value("${spring.redis.lettuce.pool.maxIdle}")
    private String maxIdle;
    @Value("${spring.redis.lettuce.pool.minIdle}")
    private String minIdle;
    @Value("${spring.redis.lettuce.pool.maxActive}")
    private String maxActive;
    @Value("${spring.redis.lettuce.pool.maxActive}")
    private String maxWait;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(Integer maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
