/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.lock;

/**
 * @author csieflyman
 */
public interface ReadWriteObject {

    void write(Object content);

    Object read();

}
