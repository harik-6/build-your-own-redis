package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleThreadedServer {
    private ServerSocket server;

    private CommandParser commandParser;

    private CommandEvaluator commandEvaluator;

    public SingleThreadedServer() {
        this.commandParser = new CommandParser();
        this.commandEvaluator = new CommandEvaluator();
    }

    public void startServer() throws IOException {
        System.out.println("Starting server at port 8000 running on thread " + Thread.currentThread().getId());
        server = new ServerSocket(8000);
        while (true) {
            Socket client = server.accept();
            System.out.println("Accepted connection from a client on port " + client.getPort());
            while (true) {
                List<String> command = commandParser.parseInput(client);
                client.getOutputStream().flush();
                System.out.println("Received command from client " + command);
                String output = commandEvaluator.evaluate(command);
                client.getOutputStream().write(commandParser.parseOutput(output));
            }
        }
    }
}
