/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author csieflyman
 */
public class ReadWriteLockReadWriteObject implements ReadWriteObject{

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private Object content;

    @Override
    public void write(Object content) {
        lock.writeLock().lock();
        try {
            this.content = content;
            System.out.println("write");
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Object read() {
        lock.readLock().lock();
        try {
            System.out.println("read");
            return content;
        } finally {
            lock.readLock().unlock();
        }
    }
}
