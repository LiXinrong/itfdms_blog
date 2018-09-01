package com.itfdms.common.bean.config.redis.service;

import redis.clients.jedis.GeoRadiusResponse;

import java.util.List;
import java.util.Map;

/**
 * @author lxr
 * @Title: JedisService
 * @ProjectName itfdms_blog
 * @Description: jedis缓存接口
 * @date 2018-08-1013:58
 */
public interface JedisService {

    /**
     * 是否缓存
     * @param key
     * @return
     */
    boolean exists(String key);


    /**
     * 缓存set值
     * @param key
     * @param value
     * @param seconds 缓存时间，默认为0
     * @return
     */
    String set(String key,String value,int seconds);


    /**
     * 重新缓存getSet
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    String getSet(String key,String value,int seconds);

    /**
     * 获取set缓存
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 添加地址位置
     * @param key
     * @param longitude
     * @param latitude
     * @param obj
     * @return
     */
    Long geoAdd(String key,double longitude,double latitude,byte[] obj);


    /**
     * 获取地理位置
     * @param key
     * @param longitude
     * @param latitude
     * @return
     */
    List<GeoRadiusResponse> georadius(String key, double longitude, double latitude);

    /**
     * 删除key
     * @param key
     */
    void delKey(String key);

    /**
     * 删除natice key
     * @param key
     */
    void delNativeKey(String key);

    /**
     * 获取Map类型数据
     * @param key
     * @return
     */
    Map<String,Object> getMapData(String key);

    /**
     * 加锁，避免key重复
     * @param key
     * @param second
     * @return
     */
    boolean lock(String key,int second);

    /**
     * 解锁
     * @param key
     * @return
     */
    void unlock(String key);

    /**
     * 统计锁定次数
     * @param key
     * @return
     */
    String getLocakValue(String key);
}
