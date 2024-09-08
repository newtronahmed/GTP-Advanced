package org.springboot.dayone;

public class Main {
    public static void main(String[] args) {
        // Create instances of the task
        Runnable counter1 = new Counter("Counter 1");
        Runnable counter2 = new Counter("Counter 2");

        // Create threads to run the tasks concurrently
        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter2);

        // Start the threads
        thread1.start();
        thread2.start();
    }
}