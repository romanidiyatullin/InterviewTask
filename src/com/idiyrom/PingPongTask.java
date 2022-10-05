package com.idiyrom;

public class PingPongTask {

    static boolean flag = true;
    static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () -> {
            int counter = 0;
            while (counter<10) {
                synchronized (o) {
                    if (flag) {
                        System.out.println("Ping!");
                        counter++;
                        flag = false;
                    }
                }
            }
        };

        Runnable r2 = () -> {
            int counter = 0;
            while (counter<10) {
                synchronized (o) {
                    if (!flag) {
                        System.out.println("Pong!");
                        counter++;
                        flag = true;
                    }
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

    }
}


