package com.lk.redisclient.impl;

import com.lk.redisclient.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSigle implements JedisClient {
    private JedisPool jedisPool;
    @Override
    public String get(String K) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(K);
        jedis.close();
        return s;
    }

    @Override
    public String set(String K, String V) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.set(K, V);
        jedis.close();
        return s;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
