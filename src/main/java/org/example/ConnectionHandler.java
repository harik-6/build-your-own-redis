package org.example;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader clientInput;
    private PrintWriter clientOutput;
    public ConnectionHandler(Socket clientSocket, BufferedReader clientInput, PrintWriter clientOutput) {
        this.clientSocket = clientSocket;
        this.clientInput = clientInput;
        this.clientOutput = clientOutput;
    }

    @Override
    public void run() {
        try {
            Long threadId = Thread.currentThread().getId();
            System.out.println("Accepted connection from a client on port " + clientSocket.getPort() + " with thread id " + threadId);
            clientOutput.println("Start sending messages to the server. Type 'exit' to close connection");
            while (true) {
                System.out.println("Waiting for input from client on port " + clientSocket.getPort());
                String clientMessage = clientInput.readLine();
                System.out.println("Message from client " + clientMessage);
                if (clientMessage == null || clientMessage.equals("exit")) {
//                    System.out.println("Closing connection from port " + clientSocket.getPort());
//                    clientOutput.println("Closing connection");
//                    clientSocket.close();
                    clientOutput.println("+OK\r\n");
                    break;
                }
//                clientOutput.println("message from server: " + clientMessage+"from thread id: "+threadId);
            }
        } catch (Exception e) {
        }
    }
}
