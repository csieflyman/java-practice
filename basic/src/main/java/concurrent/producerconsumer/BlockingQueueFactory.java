/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author csieflyman
 */
public class BlockingQueueFactory implements Factory {

    private BlockingQueue queue = new LinkedBlockingDeque<>();

    @Override
    public void produce() {

    }

    @Override
    public void consume() {

    }
}
