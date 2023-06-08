package org.example.threadwait;

public class Print implements Runnable {
    static final Object lock = new Object();
    private String lockedBy = "Thread-1";
    private void randomDelay() {
        try {
            Thread.sleep((long) (Math.random()*10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitState() throws InterruptedException {
        String name = Thread.currentThread().getName();
        synchronized (lock) {
            if (name.equals("Thread-1")){
                System.out.printf("Hello from the thread %s executing lock.notify()  \n", name);
                lock.notify();
            }
            System.out.printf("Hello from the thread %s before lock.wait \n", name);
            lock.wait();
            System.out.printf("Hello from the thread %s after lock.wait \n",name);
        }
    }

    private void printAlternate() throws InterruptedException {
        String currentThread = Thread.currentThread().getName();
        while (true) {
            synchronized (lock) {
               if(lockedBy.equals(currentThread)) {
                   System.out.printf("%s current executing thread \n", currentThread);
                   lock.notify();
               }
               System.out.printf("%s going into lock \n", currentThread);
               lockedBy = currentThread;
               lock.wait();
            }
        }
    }
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            printAlternate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Hello from the thread %s and waited %sms \n", Thread.currentThread().getName(), System.currentTimeMillis()-start);
    }
}
