/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.deadlock;

/**
 * @author csieflyman
 */
public class DeadLockResource implements Resource<DeadLockResource>{

    @Override
    public synchronized void cooperate(DeadLockResource resource) {
        resource.doJob();
    }

    @Override
    public synchronized void doJob() {
    }
}
