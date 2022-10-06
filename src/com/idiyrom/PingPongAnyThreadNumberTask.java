package com.idiyrom;

import java.util.Random;

public class PingPongAnyThreadNumberTask {

    static int marker = 0;

    public static void main(String[] args)  {

        Random random = new Random();
        Object o = new Object();
        
        int numberOfThreads = 5;
        int howManyLinesPrintPerThread = 3;

        Runnable r = () -> {
            int counter = 0;
            while (counter<howManyLinesPrintPerThread) {
                try {
                    Thread.sleep(random.nextLong(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o) {
                    int threadId = Integer.parseInt(Thread.currentThread().getName());
                    if(marker == threadId) {
                        System.out.println("Hello from Thread# " + threadId);
                        counter++;
                        marker = threadId==numberOfThreads-1?0:threadId+1;
                        }
                    }
                }
            };

        for(int i=0; i<numberOfThreads; i++){
            Thread thread = new Thread(r);
            thread.setName(""+i);
            thread.start();
        }
    }
}


