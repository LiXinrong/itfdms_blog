package com.itfdms.common.bean.config.redis;

import io.swagger.models.auth.In;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import javax.annotation.Resource;
import java.time.Duration;
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
@ConditionalOnClass(RedisConfigrationProperties.class)
@EnableConfigurationProperties(RedisConfigrationProperties.class)
public class RedisFactoryConfig {

    @Autowired
    private RedisConfigrationProperties redisConfigrationProperties;

    @Bean
    public RedisConnectionFactory myLettuceConnectionFactory() {
        // 集群版配置
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();

        Set<RedisNode> nodes = new HashSet<RedisNode>();

        for (String ipPort : redisConfigrationProperties.getNodes()) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }
        redisClusterConfiguration.setPassword(RedisPassword.of(redisConfigrationProperties.getPassword()));
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(redisConfigrationProperties.getMaxRedirects());

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(Long.parseLong(redisConfigrationProperties.getTimeout())))
                .poolConfig(genericObjectPoolConfig())
                .build();

        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisClusterConfiguration, clientConfig);
        return factory;
    }


    /**
     * GenericObjectPoolConfig 连接池配置
     *
     * @return
     */
    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(Integer.parseInt(redisConfigrationProperties.getMaxIdle()));
        genericObjectPoolConfig.setMinIdle(Integer.parseInt(redisConfigrationProperties.getMinIdle()));
        genericObjectPoolConfig.setMaxTotal(Integer.parseInt(redisConfigrationProperties.getMaxActive()));
        genericObjectPoolConfig.setMaxWaitMillis(Integer.parseInt(redisConfigrationProperties.getMaxWait()));
        return genericObjectPoolConfig;
    }


}
