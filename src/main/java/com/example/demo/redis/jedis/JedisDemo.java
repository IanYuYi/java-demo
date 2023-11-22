package com.example.demo.redis.jedis;

import redis.clients.jedis.Jedis;

public class JedisDemo {
    private Jedis getJedis() {
        Jedis jedis = new Jedis("ip", 8080);
        jedis.auth("123456密码");
        jedis.select(0);
        return jedis;
    }

    public void work() {
        Jedis jedis = getJedis();
        try {
            jedis.set("test", "value");
            jedis.get("test");
        } catch (Exception e) {

        } finally {
            jedis.close();
        }
    }
}
