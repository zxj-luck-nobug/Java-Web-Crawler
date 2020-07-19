package com.crawler.cache;


import redis.clients.jedis.Jedis;

public class StringCache {

    private static final Jedis client = RedisConfig.getJedis();

    public static void setKV(String key,String value){
        client.set(key, value);
    }

    public static String getV(String key){
       return client.get(key);
    }

    public static void lpush(String key,String ...values){
        client.lpush(key,values);
    }

    public static String lpop(String key){
        return client.lpop(key);
    }
}
