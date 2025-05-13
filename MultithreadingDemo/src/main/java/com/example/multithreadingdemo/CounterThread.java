package com.example.multithreadingdemo;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterThread extends Thread {
    private final AtomicInteger counter = new AtomicInteger(0);
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            counter.incrementAndGet();
        }
    }

    public int getCounter() {
        return counter.get();
    }

    public void stopCounting() {
        running = false;
    }
}