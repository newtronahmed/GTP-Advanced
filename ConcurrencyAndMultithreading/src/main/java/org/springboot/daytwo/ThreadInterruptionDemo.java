package org.springboot.daytwo;

public class ThreadInterruptionDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is running...");
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted during sleep");
                    Thread.currentThread().interrupt(); // Re-interrupt the thread
                }
            }
            System.out.println("Thread exiting...");
        });

        thread.start();

        try {
            Thread.sleep(3000); // Main thread sleeps for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // Interrupt the thread
    }
}

