package org.springboot.daythree.deadlock;

public class DeadlockDemo {
    static class Resource {
        public synchronized void acquire(Resource other) {
            System.out.println(Thread.currentThread().getName() + " acquired " + this);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            other.use();
        }

        public synchronized void use() {
            System.out.println(Thread.currentThread().getName() + " is using " + this);
        }
    }

    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        Thread thread1 = new Thread(() -> resource1.acquire(resource2), "Thread 1");
        Thread thread2 = new Thread(() -> resource2.acquire(resource1), "Thread 2");

        thread1.start();
        thread2.start();
    }
}
