package org.springboot.dayfive;
import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    // Producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (queue.size() == CAPACITY) {
                    wait(); // Wait if the queue is full
                }
                queue.add(value);
                System.out.println("Produced: " + value);
                value++;
                notify(); // Notify consumer thread
                Thread.sleep(1000); // Simulate time delay
            }
        }
    }

    // Consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait(); // Wait if the queue is empty
                }
                int value = queue.poll();
                System.out.println("Consumed: " + value);
                notify(); // Notify producer thread
                Thread.sleep(1000); // Simulate time delay
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
    }
}

