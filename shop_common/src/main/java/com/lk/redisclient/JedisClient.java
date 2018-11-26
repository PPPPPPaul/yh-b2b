package com.lk.redisclient;

public interface JedisClient {
    String get(String K);
    String set(String K,String V);
}
