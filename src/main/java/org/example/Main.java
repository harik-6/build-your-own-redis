package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        MultiThreadedServer multiThreadedServer = new MultiThreadedServer();
//        try {
//            multiThreadedServer.startServer();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        SingleThreadedServer singleThreadedServer = new SingleThreadedServer();
        try {
            singleThreadedServer.startServer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}