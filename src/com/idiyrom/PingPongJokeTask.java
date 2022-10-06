package com.idiyrom;

public class PingPongJokeTask {

    static byte marker = 0;
    static Object o = new Object();

    public static void main(String[] args)  {

        Runnable r1 = () -> {
            int counter = 0;
            while (counter<10) {
                synchronized (o) {
                    if (marker==0) {
                        System.out.println("Ping!");
                        counter++;
                        marker=1;
                    }
                }
            }
        };

        Runnable r2 = () -> {
            int counter = 0;
            while (counter<10) {
                synchronized (o) {
                    if (marker==1) {
                        System.out.println("Pong!");
                        counter++;
                        marker = 2;
                    }
                }
            }
        };

        Runnable r3 = () -> {
            int counter = 0;
            while (counter<10) {
                synchronized (o) {
                    if (marker==2) {
                        System.out.println("Joke!");
                        counter++;
                        marker = 0;
                    }
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        t1.start();
        t2.start();
        t3.start();

    }
}


