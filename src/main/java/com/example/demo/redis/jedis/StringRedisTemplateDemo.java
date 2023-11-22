package com.example.demo.redis.jedis;

import com.example.demo.redis.jedis.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

public class StringRedisTemplateDemo {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void work() throws JsonProcessingException {
        stringRedisTemplate.opsForValue().set("a", "b");
        stringRedisTemplate.opsForHash().put("hash", "a", "b");
        stringRedisTemplate.opsForHash().putAll("hash", new HashMap<>());
        //取全部hash
        Map<Object, Object> a = stringRedisTemplate.opsForHash().entries("a");

        String json = objectMapper.writeValueAsString(new User());
        stringRedisTemplate.opsForValue().set("user.1", json);
        String result = stringRedisTemplate.opsForValue().get("user.1");
    }
}
