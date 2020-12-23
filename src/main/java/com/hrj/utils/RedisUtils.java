package com.hrj.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Slf4j
@Component
public class RedisUtils {
    @Autowired
    StringRedisTemplate redisTemplate;

    public void setRedis(String key,String value,long time){
         redisTemplate.opsForValue().set(key,value, time,TimeUnit.SECONDS);
         log.info("设置成功");
    }

    public String getRedis(String key){
       String v= redisTemplate.opsForValue().get(key);
        log.info("获取成功"+v);
        return  v;
    }

}
