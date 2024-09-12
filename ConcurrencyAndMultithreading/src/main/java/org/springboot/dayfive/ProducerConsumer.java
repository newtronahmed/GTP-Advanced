package org.springboot.dayfive;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;
    private final AtomicInteger totalProduced = new AtomicInteger(0);
    private final int MAX_ITEMS = 20; // Total items to produce before stopping

    // Producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (totalProduced.get() < MAX_ITEMS) {
            synchronized (this) {
                while (queue.size() == CAPACITY) {
                    wait(); // Wait if the queue is full
                }
                queue.add(value);
                System.out.println("Produced: " + value);
                value++;
                totalProduced.incrementAndGet();
                notifyAll(); // Notify all waiting threads
                Thread.sleep(100); // Reduced sleep time for faster execution
            }
        }
    }

    // Consumer thread
    public void consume() throws InterruptedException {
        while (totalProduced.get() < MAX_ITEMS || !queue.isEmpty()) {
            synchronized (this) {
                while (queue.isEmpty() && totalProduced.get() < MAX_ITEMS) {
                    wait(); // Wait if the queue is empty and production isn't finished
                }
                if (!queue.isEmpty()) {
                    int value = queue.poll();
                    System.out.println("Consumed: " + value);
                    notifyAll(); // Notify all waiting threads
                    Thread.sleep(100); // Reduced sleep time for faster execution
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Production and consumption completed.");
    }
}