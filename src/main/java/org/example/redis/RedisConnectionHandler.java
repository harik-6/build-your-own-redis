package org.example.redis;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class RedisConnectionHandler extends Thread{
    private Socket client;
    private RedisCommandParser redisCommandParser;
    private RedisCommandEvaluator redisCommandEvaluator;

    RedisConnectionHandler(Socket client) {
        this.client = client;
        this.redisCommandParser = new RedisCommandParser();
        this.redisCommandEvaluator = new RedisCommandEvaluator();
    }
     @Override
    public void run()  {
        System.out.printf("Connection received from on port %s by thread %s\n",client.getPort(),Thread.currentThread().getId());
        while (true) {
            try {
                List<String> command = redisCommandParser.parseInput(client);
                client.getOutputStream().flush();
                System.out.println("Received command from client " + command);
                String output = redisCommandEvaluator.evaluate(command);
                client.getOutputStream().write(redisCommandParser.parseOutput(output));
                if("EXIT".equals(output)) {
                    System.out.println("Closing connection with client on port "+client.getPort());
                    client.close();
                    break;
                }
            }catch (Exception ex) {
                System.out.println("Error while processing client request "+ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
