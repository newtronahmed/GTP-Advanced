package org.springboot.dayfour;

class VolatileCounter {
    private volatile int count = 0;

    public void increment() {
        count++; // This operation is not atomic
    }

    public int getCount() {
        return count;
    }
}
