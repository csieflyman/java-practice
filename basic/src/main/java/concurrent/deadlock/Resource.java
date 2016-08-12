/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.deadlock;

/**
 * @author csieflyman
 */
public interface Resource<T> {

    void cooperate(T resource);

    void doJob();
}
