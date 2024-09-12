package org.springboot.dayfive;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


class OrderSystem {
    private final BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(5);
    private final AtomicInteger orderIdCounter = new AtomicInteger(1);
    private final int TOTAL_ORDERS = 20;
    private volatile boolean isProducing = true;

    class OrderProducer implements Runnable {
        @Override
        public void run() {
            try {
                while (orderIdCounter.get() <= TOTAL_ORDERS) {
                    Order order = new Order(orderIdCounter.getAndIncrement(), "Item-" + (char)('A' + (orderIdCounter.get() % 26)));
                    orderQueue.put(order);
                    System.out.println("Produced: " + order);
                    Thread.sleep(100);
                }
                isProducing = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    class OrderProcessor implements Runnable {
        @Override
        public void run() {
            try {
                while (isProducing || !orderQueue.isEmpty()) {
                    Order order = orderQueue.poll();
                    if (order != null) {
                        System.out.println("Processing: " + order);
                        Thread.sleep(200);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void startProcessing() {
        Thread producerThread = new Thread(new OrderProducer());
        Thread processorThread = new Thread(new OrderProcessor());

        producerThread.start();
        processorThread.start();

        try {
            producerThread.join();
            processorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Order processing completed.");
    }

    public static void main(String[] args) {
        new OrderSystem().startProcessing();
    }
}