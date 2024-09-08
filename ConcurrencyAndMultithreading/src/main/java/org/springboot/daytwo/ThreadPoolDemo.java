package org.springboot.daytwo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to the executor
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                System.out.println("Task running on: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);  // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the executor
        executorService.shutdown();
        System.out.println("All tasks submitted.");
    }
}
