package com.example.megaverse.tasks;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.megaverse.astral.services.AstralServiceManager;

@Configuration
public class TaskManager {

    @Value("${task.worker.thread.count}")
    private int threadCount;

    private final TaskQueue taskQueue;

    @Autowired
    private AstralServiceManager serviceManager;

    public TaskManager(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadCount);
        executor.setMaxPoolSize(threadCount);
        executor.setQueueCapacity(100);
        executor.initialize();
        return executor;
    }

    public void startWorkers() {
        for (int i = 0; i < threadCount; i++) {
            taskExecutor().execute(new Worker(taskQueue, serviceManager));
        }
    }
}
