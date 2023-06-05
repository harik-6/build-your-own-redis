package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    private ServerSocket server;

    public void startServer() throws IOException {
        System.out.println("Starting server at port 8000 running on thread " + Thread.currentThread().getId());
        server = new ServerSocket(8001);
        while (true) {
            System.out.println("Waiting for a client ...");
            Socket client = server.accept();
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter clientOutput = new PrintWriter(client.getOutputStream(), true);
            Thread connectionHandlerThread = new ConnectionHandler(client,clientInput,clientOutput);
            connectionHandlerThread.start();
            System.out.println("Done with a client ...");
        }
    }
}
