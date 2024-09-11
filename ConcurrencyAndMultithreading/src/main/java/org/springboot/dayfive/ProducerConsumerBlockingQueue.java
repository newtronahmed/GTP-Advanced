package org.springboot.dayfive;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerConsumerBlockingQueue {
    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    // Producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            queue.put(value); // Puts the element, waiting if necessary
            System.out.println("Produced: " + value);
            value++;
            Thread.sleep(1000); // Simulate time delay
        }
    }

    // Consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            int value = queue.take(); // Takes the element, waiting if necessary
            System.out.println("Consumed: " + value);
            Thread.sleep(1000); // Simulate time delay
        }
    }

    public static void main(String[] args) {
        ProducerConsumerBlockingQueue pc = new ProducerConsumerBlockingQueue();

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
