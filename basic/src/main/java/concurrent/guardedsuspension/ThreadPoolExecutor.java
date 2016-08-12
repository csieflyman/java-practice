/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.guardedsuspension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author csieflyman
 */
public class ThreadPoolExecutor {

    private int corePoolSize = 1;

    private List<Worker> workers = new ArrayList<>();

    private BlockingQueue<FutureTask> taskQueue = new LinkedBlockingQueue<>();

    public ThreadPoolExecutor(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void start() {
        for (int i = 0; i < corePoolSize; i++) {
            Worker worker = new Worker("thread-" + i, this);
            workers.add(worker);
            worker.start();
        }
    }

    public void submit(FutureTask task) {
        System.out.println("submit task... input = " + task.getInput());
        taskQueue.offer(task);
    }

    public void shutdown() {
        workers.stream().forEach(worker -> worker.shutdown());
        taskQueue.clear();
    }

    public BlockingQueue<FutureTask> getTaskQueue() {
        return taskQueue;
    }
}
