package org.example.threadwait;

public class ThreadWaiting {
    public static void main(String[] args) {
        int threads = 2;
        for (int i=0;i<threads;i++) {
            Thread t = new Thread(new Print());
            t.start();
        }
    }
}
