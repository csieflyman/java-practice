/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.guardedsuspension;

import java.util.concurrent.BlockingQueue;

/**
 * @author csieflyman
 */
public class Worker extends Thread {

    private ThreadPoolExecutor pool;

    private FutureTask task;

    private boolean isShutdown = false;

    private BlockingQueue<FutureTask> taskQueue;

    public Worker(String name, ThreadPoolExecutor pool) {
        super(name);
        this.pool = pool;
        taskQueue = pool.getTaskQueue();
    }

    @Override
    public void run() {
        while(!isShutdown) {
            try {
                FutureTask task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(this.getName() + " isInterrupted = " + this.isInterrupted());
            }
        }
    }

    public synchronized void shutdown() {
        isShutdown = true;
        this.interrupt();
    }
}
