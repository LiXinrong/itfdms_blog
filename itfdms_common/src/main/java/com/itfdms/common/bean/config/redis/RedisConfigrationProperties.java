package com.itfdms.common.bean.config.redis;

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
@ConfigurationProperties(prefix = "spring.redis.database.cluster")
public class RedisConfigrationProperties {

    private List<String> nodes = new ArrayList<>();

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
