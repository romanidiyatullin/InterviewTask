package com.idiyrom;

public class PongPing {
    public static void main(String[] args) {

        Object locker = new Object();

        Thread ping = new Thread(new MyRunnable("PING", locker));
        ping.start();

        Thread pong = new Thread(new MyRunnable("PONG", locker));
        pong.start();
    }
}

class MyRunnable implements Runnable {
    String text;
    Object locker;

    MyRunnable(String txt, Object o) {
        text = txt;
        locker = o;
    }

    public void run() {
        synchronized (locker) {
            while (true) {
                System.out.println(text);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                locker.notify();
                try {
                    locker.wait();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}