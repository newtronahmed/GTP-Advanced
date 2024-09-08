package org.springboot.daytwo;

public class ThreadManagementDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread running: " + i);
                try {
                    Thread.sleep(1000);  // Sleep for 1 second
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        });

        thread.start();

        try {
            thread.join();  // Wait for this thread to finish
            System.out.println("Main thread resumes after child thread finishes");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
