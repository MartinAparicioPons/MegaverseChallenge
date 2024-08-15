package com.example.megaverse.tasks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

@Component
public class TaskQueue {
    private final BlockingQueue<Task> queue;

    public TaskQueue() {
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * (From {@link BlockingQueue})
     * Inserts the specified element into this queue, waiting if necessary
     * for space to become available.
     *
     * @param e the element to add
     * @throws InterruptedException     if interrupted while waiting
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this
     *                                  queue
     */
    public void enqueue(Task task) throws InterruptedException {
        queue.put(task);
    }

    /**
     * (From {@link BlockingQueue})
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element becomes available.
     *
     * @return the head of this queue
     * @throws InterruptedException if interrupted while waiting
     */
    public Task dequeue() throws InterruptedException {
        return queue.take();
    }
}
