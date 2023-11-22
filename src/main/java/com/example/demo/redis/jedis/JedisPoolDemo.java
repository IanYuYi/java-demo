package com.example.demo.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolDemo {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大链接
        config.setMaxTotal(1000);
        // 最大空闲链接
        config.setMaxIdle(500);
        // 最小空闲链接
        config.setMinIdle(10);
        // 最大等待时长
        config.setMaxWaitMillis(1000);
        jedisPool = new JedisPool(config, "ip", 8080, 1000, "密码");
    }

    public void work() {
        // 获取jedis
        Jedis resource = jedisPool.getResource();
    }

}
