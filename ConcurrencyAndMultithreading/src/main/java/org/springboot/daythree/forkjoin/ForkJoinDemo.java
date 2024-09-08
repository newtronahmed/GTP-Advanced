package org.springboot.daythree.forkjoin;

import java.util.concurrent.ForkJoinPool;



public class ForkJoinDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(arr, 0, arr.length);
        int result = pool.invoke(task); // Invoke the main task
        System.out.println("Sum: " + result); // Output: Sum: 36
    }
}
