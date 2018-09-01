package com.itfdms.common.bean.config.redis.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

/**
 * @author lxr
 * @Title: JedisServiceImpl
 * @ProjectName itfdms_blog
 * @Description: Jedis操作实现类
 * @date 2018-08-1014:09
 */

@Service
public class JedisServiceImpl implements JedisService {

    @Autowired
    private JedisCluster jedisCluster;


    @Override
    public boolean exists(String key) {
        boolean flag = false;
        flag = jedisCluster.exists(key);
        return flag;
    }

    @Override
    public String set(String key, String value, int seconds) {
        String responseResult = jedisCluster.set(key, value);
        if (0 != seconds) {
            jedisCluster.expire(key, seconds);
        }
        return responseResult;
    }

    @Override
    public String getSet(String key, String value, int seconds) {
        String jedisClusterSet = jedisCluster.set(key, value);
        jedisCluster.expire(key, seconds);
        return jedisClusterSet;
    }

    @Override
    public String get(String key) {
        String str = jedisCluster.get(key);
        return str;
    }

    @Override
    public Long geoAdd(String key, double longitude, double latitude, byte[] obj) {
        return null;
    }

    @Override
    public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude) {
        return null;
    }

    @Override
    public void delKey(String key) {
        jedisCluster.del(key);
    }

    @Override
    public void delNativeKey(String key) {
        jedisCluster.del(key);
    }

    @Override
    public Map<String, Object> getMapData(String key) {
        String str = jedisCluster.get(key);
        Map<String, Object> map = JSONObject.parseObject(str, Map.class);
        return map;
    }

    @Override
    public boolean lock(String key, int second) {
        if (jedisCluster.incr(key) == 1) {
            jedisCluster.expire(key, second);
            return false;
        }
        return true;
    }

    @Override
    public void unlock(String key) {
        jedisCluster.del(key);
    }

    @Override
    public String getLocakValue(String key) {
        return jedisCluster.get(key);
    }
}
