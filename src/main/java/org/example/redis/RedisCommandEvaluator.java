package org.example.redis;

import org.example.redis.RedisCache;

import java.util.List;

public class RedisCommandEvaluator {
    private RedisCache redisCache;
    RedisCommandEvaluator() {
        redisCache = new RedisCache();
    }
    private String executeCommand(List<String> args) {
        return "OK";
    }

    private String executePing(List<String> args) {
        if(args.isEmpty()) return "PONG";
        StringBuilder output = new StringBuilder();
        for(String arg: args) {
            output.append(arg).append(" ");
        }
        return output.toString();
    }

    private String executeSet(List<String> args) {
        redisCache.set(args.get(0),args.get(1));
        return "OK";
    }

    private String executeGet(List<String> args) {
        return redisCache.get(args.get(0));
    }

    public String evaluate(List<String> command) {
        if(command.isEmpty()) return "OK";
        String commandName = command.get(1);
        switch (commandName) {
            case "COMMAND":
                return executeCommand(command.subList(2, command.size()));
            case "PING":
                return executePing(command.subList(2, command.size()));
            case "SET":
                return executeSet(command.subList(2, command.size()));
            case "GET":
                return executeGet(command.subList(2, command.size()));
             case "EXIT":
                return "EXIT";
            default:
                return "OK";
        }
    }
}
