package com.example.demo.redis.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

public class RedisTemplateDemo {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void work() {
        redisTemplate.opsForValue().set("a", "b");
        redisTemplate.opsForHash().putAll("hash", new HashMap<>());
        Map<Object, Object> hash = redisTemplate.opsForHash().entries("hash");
    }
}
