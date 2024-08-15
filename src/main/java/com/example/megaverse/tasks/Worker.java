package com.example.megaverse.tasks;

import com.example.megaverse.astral.Astral;
import com.example.megaverse.astral.services.AstralServiceManager;

public class Worker implements Runnable {
    private final TaskQueue taskQueue;
    private final AstralServiceManager serviceManager;

    public Worker(TaskQueue taskQueue, AstralServiceManager serviceManager) {
        this.taskQueue = taskQueue;
        this.serviceManager = serviceManager;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = taskQueue.dequeue();
                process(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Worker interrupted");
        }
    }

    private void process(Task task) {
        System.out.println(task);
        Astral astral = task.getAstral();
        if (task.getAction() == Task.Action.ADD) {
            serviceManager.create(astral);
        } else if (task.getAction() == Task.Action.DELETE) {
            serviceManager.delete(astral);
        }
    }
}
