package org.example;

import java.util.List;

public class CommandEvaluator {
    private String executeCommand(List<String> args) {
        System.out.println("Executing COMMAND "+args);
        return "OK";
    }

    private String executePing(List<String> args) {
        System.out.println("Executing PING "+args);
        if(args.isEmpty()) return "PONG";
        StringBuilder output = new StringBuilder();
        for(String arg: args) {
            output.append(arg).append(" ");
        }
        return output.toString();
    }

    public String evaluate(List<String> command) {
        if(command.isEmpty()) return "OK";
        String commandName = command.get(1);
        switch (commandName) {
            case "COMMAND":
                return executeCommand(command.subList(2, command.size()));
            case "PING":
                return executePing(command.subList(2, command.size()));
            default:
                return "OK";
        }
    }
}
