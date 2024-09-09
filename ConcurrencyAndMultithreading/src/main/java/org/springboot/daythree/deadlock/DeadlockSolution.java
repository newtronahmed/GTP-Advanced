package org.springboot.daythree.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolution {
    static class Resource {
        private final ReentrantLock lock = new ReentrantLock();

        public boolean tryAcquire(Resource other) {
            boolean acquired = false;
            try {
                // Try to acquire the lock on the current resource
                if (lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " acquired " + this);
                    Thread.sleep(50); // Simulate some work

                    // Try to acquire the lock on the other resource
                    if (other.lock.tryLock()) {
                        other.use();
                        acquired = true;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Reset interrupt flag
                System.err.println(Thread.currentThread().getName() + " was interrupted.");
            } finally {
                // Release locks if they are held
                if (acquired) {
                    other.lock.unlock();
                }
                if (lock.isHeldByCurrentThread()) { // Correct usage with ReentrantLock
                    lock.unlock();
                }
            }
            return acquired;
        }

        public void use() {
            System.out.println(Thread.currentThread().getName() + " is using " + this);
        }

        @Override
        public String toString() {
            return "Resource@" + Integer.toHexString(hashCode());
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
