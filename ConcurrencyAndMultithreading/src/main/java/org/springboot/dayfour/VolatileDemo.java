package org.springboot.dayfour;


public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        VolatileCounter counter = new VolatileCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count (may not be 2000): " + counter.getCount());
    }
}
