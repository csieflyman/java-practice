/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.threadpool;

/**
 * @author csieflyman
 */
public class Worker extends Thread {

    private ThreadPoolExecutor pool;

    private FutureTask task;

    private boolean isShutdown = false;

    public Worker(String name, ThreadPoolExecutor pool) {
        super(name);
        this.pool = pool;
    }

    @Override
    public void run() {
        while(!isShutdown) {
            if(task != null) {
                System.out.println(this.getName() + " process task ");
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pool.repool(this);
            }

            synchronized (this) {
                System.out.println(this.getName() + " wait for task ");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void setTask(FutureTask task) {
        this.task = task;
        notify();
    }

    public synchronized void shutdown() {
        isShutdown = true;
        notify();
    }
}
