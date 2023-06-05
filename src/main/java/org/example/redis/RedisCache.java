package org.example.redis;

import java.util.HashMap;
import java.util.Map;

public class RedisCache {
    private static final Map<String,String> memory = new HashMap<>();

    public String get(String key) {
        System.out.printf("Getting key %s from cache by thread %s\n",key,Thread.currentThread().getId());
        return memory.getOrDefault(key,null);
    }


    public synchronized void set(String key, String value) {
        memory.put(key,value);
    }
}
