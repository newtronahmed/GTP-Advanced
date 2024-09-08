package org.springboot.daythree.forkjoin;

import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {
    private final int[] arr;
    private final int start, end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= 3) {  // Base case threshold
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {  // Split task
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(arr, start, mid);
            SumTask rightTask = new SumTask(arr, mid, end);
            leftTask.fork(); // Asynchronously execute left task
            int rightResult = rightTask.compute(); // Compute right task
            int leftResult = leftTask.join(); // Wait and get result of left task
            return leftResult + rightResult;
        }
    }
}