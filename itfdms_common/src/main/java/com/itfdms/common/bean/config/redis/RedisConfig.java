package com.itfdms.common.bean.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lxr
 * @Title: RedisConfiguration
 * @ProjectName itfdms_blog
 * @Description: Redis配置中心
 * @date 2018-08-1014:24
 */

@Configuration
public class RedisConfig {

    @Resource
    private RedisConfigrationProperties redisConfigrationProperties;

    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodeSet = new HashSet<>();
        for (String node : redisConfigrationProperties.getNodes()) {
            String[] split = node.split("-");
            nodeSet.add(new HostAndPort(split[0],Integer.valueOf(split[1])));
        }
        return new JedisCluster(nodeSet);
    }

}
