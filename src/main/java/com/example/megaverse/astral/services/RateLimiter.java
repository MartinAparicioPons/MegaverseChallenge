package com.example.megaverse.astral.services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private int tokens;
    private final int maxTokens;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public RateLimiter(int maxTokens, int refillRateMs) {
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        scheduler.scheduleAtFixedRate(this::refill, refillRateMs, refillRateMs, TimeUnit.MILLISECONDS);
    }

    public synchronized boolean tryAcquire() {
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    private synchronized void refill() {
        if (tokens < maxTokens) {
            tokens++;
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
