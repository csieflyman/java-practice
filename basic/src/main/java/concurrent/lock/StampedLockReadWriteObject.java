/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author csieflyman
 */
public class StampedLockReadWriteObject implements ReadWriteObject{

    private StampedLock lock = new StampedLock();

    private Object content;

    @Override
    public void write(Object content) {
        long stamp = lock.writeLock();
        try {
            this.content = content;
            System.out.println("write");
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public Object read() {
        long stamp = lock.tryOptimisticRead();
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                System.out.println("read again");
                return content;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        System.out.println("read");
        return content;
    }
}
