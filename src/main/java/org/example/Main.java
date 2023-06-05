package org.example;

import org.example.redis.RedisServer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RedisServer redisServer = new RedisServer();
        try {
            redisServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}