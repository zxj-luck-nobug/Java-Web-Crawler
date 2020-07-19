package com.crawler.cache;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConfig {

    private static final JedisPoolConfig conf = new JedisPoolConfig();
    private static JedisPool pool;
    static {
        conf.setMinIdle(1);
        conf.setMaxIdle(10);
        conf.setBlockWhenExhausted(true);
        pool = new JedisPool(conf,"39.106.113.6",6380,1000,"Xwcxwj6688");
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }
}
