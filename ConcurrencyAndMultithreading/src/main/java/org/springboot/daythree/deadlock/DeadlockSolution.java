package org.springboot.daythree.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolution {
    static class Resource {
        private final Lock lock = new ReentrantLock();

        public boolean tryAcquire(Resource other) {
            try {
                if (lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " acquired " + this);
                    Thread.sleep(50); // Simulate some work

                    if (other.lock.tryLock()) {
                        other.use();
                        other.lock.unlock();
                        return true;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return false;
        }

        public void use() {
            System.out.println(Thread.currentThread().getName() + " is using " + this);
        }
    }

    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        Thread thread1 = new Thread(() -> {
            while (!resource1.tryAcquire(resource2)) {
                System.out.println(Thread.currentThread().getName() + " retrying...");
            }
        }, "Thread 1");

        Thread thread2 = new Thread(() -> {
            while (!resource2.tryAcquire(resource1)) {
                System.out.println(Thread.currentThread().getName() + " retrying...");
            }
        }, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
