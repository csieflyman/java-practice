/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author csieflyman
 */
public class ThreadPoolExecutor {

    private int corePoolSize = 1;

    private BlockingQueue<Worker> workerQueue;

    private boolean isShutdown = false;

    public ThreadPoolExecutor(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        workerQueue = new ArrayBlockingQueue(corePoolSize);
    }

    public void start() {
        for (int i = 0; i < corePoolSize; i++) {
            Worker worker = new Worker("thread-" + i, this);
            workerQueue.offer(worker);
            worker.start();
        }
    }

    public void submit(FutureTask task) {
        try {
            System.out.println("submit task... input = " + task.getInput());
            Worker worker = workerQueue.take();
            worker.setTask(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // shutdown method may be called when worker is processing task
    public void repool(Worker worker) {
        if(!isShutdown) {
            System.out.println(worker.getName() + " repool ");
            workerQueue.offer(worker);
        }
        else {
            worker.shutdown();
        }
    }

    public void shutdown() {
        isShutdown = true;
        while(!workerQueue.isEmpty()) {
            Worker worker = workerQueue.poll();
            worker.shutdown();
        }
    }
}
