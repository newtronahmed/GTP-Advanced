package org.springboot;
class Counter implements Runnable {
    private String name;

    public Counter(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + ": " + i);

            // Sleep to simulate time-consuming task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
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