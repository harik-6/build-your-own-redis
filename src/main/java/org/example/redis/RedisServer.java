package org.example.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisServer {
    private ServerSocket server;
    private final int port = 8003;
    public void startServer() throws IOException {
        System.out.printf("Starting redis server on port %s \n",port);
        server = new ServerSocket(port);
        while (true) {
            Socket client = server.accept();
            RedisConnectionHandler handler = new RedisConnectionHandler(client);
            handler.start();
        }
    }
}
