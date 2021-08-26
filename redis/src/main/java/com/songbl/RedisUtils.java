package com.songbl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    private final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    private final static long NOT_EXPIRE = -1;



    public String get(String key, long expire) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (expire != NOT_EXPIRE) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }


    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }







    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public Object hGet(String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }


    public void hPut(String key, String hashKey, String value, long expire) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
        if (expire != NOT_EXPIRE) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
}
