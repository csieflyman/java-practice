/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author csieflyman
 */
public class NoDeadLockResource implements Resource<NoDeadLockResource>{

    private String name;

    public NoDeadLockResource(String name) {
        this.name = name;
    }

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void cooperate(NoDeadLockResource resource) {
        while(true) {
            try {
                if (this.lock.tryLock() && resource.lock.tryLock()) {
                    System.out.println(this.name + " do the job");
                    this.doJob();
                    resource.doJob();
                    break;
                }
            } finally {
                if(this.lock.isHeldByCurrentThread()) {
                    this.lock.unlock();
                }
                if(resource.lock.isHeldByCurrentThread()) {
                    resource.lock.unlock();
                }
            }
        }
    }

    @Override
    public void doJob() {

    }
}
