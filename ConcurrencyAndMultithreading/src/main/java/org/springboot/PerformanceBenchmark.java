package org.springboot;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class PerformanceComparison {
    public static void main(String[] args) {
        // Non-concurrent collection
        Map<String, Integer> hashMap = new HashMap<>();
        // Concurrent collection
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Measure performance of HashMap
        long startTime = System.nanoTime();
        performOperations(hashMap);
        long endTime = System.nanoTime();
        System.out.println("HashMap Execution Time: " + (endTime - startTime) + " ns");

        // Measure performance of ConcurrentHashMap
        startTime = System.nanoTime();
        performOperations(concurrentMap);
        endTime = System.nanoTime();
        System.out.println("ConcurrentHashMap Execution Time: " + (endTime - startTime) + " ns");
    }

    private static void performOperations(Map<String, Integer> map) {
        Runnable task = () -> {
            IntStream.range(0, 1000).forEach(i -> map.put(Thread.currentThread().getName() + i, i));
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
