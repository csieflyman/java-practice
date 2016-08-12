/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author csieflyman
 */
public class ConditionFactory implements Factory {
    private int maxSize = 5;
    private volatile int productCount = 0;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition pCondition = lock.newCondition();
    private Condition cCondition = lock.newCondition();

    @Override
    public void produce() {
        lock.lock();
        try {
            producerWaitIfFull();
            productCount++;
            System.out.println(Thread.currentThread().getName() + " produce " + productCount);
            signalConsumerIfNotEmpty();
        } finally {
            lock.unlock();
        }
    }

    private void producerWaitIfFull() {
        while (productCount >= maxSize) {
            pCondition.awaitUninterruptibly();
        }
    }

    private void signalConsumerIfNotEmpty() {
        if (productCount > 0) {
            cCondition.signalAll();
        }
    }

    @Override
    public void consume() {
        lock.lock();
        try {
            consumerWaitIfEmpty();
            productCount--;
            System.out.println(Thread.currentThread().getName() + " consume " + productCount);
            signalProducerIfNotFull();
        } finally {
            lock.unlock();
        }
    }

    private void consumerWaitIfEmpty() {
        while (productCount == 0) {
            cCondition.awaitUninterruptibly();
        }
    }

    private void signalProducerIfNotFull() {
        if (productCount < maxSize) {
            pCondition.signalAll();
        }
    }
}
