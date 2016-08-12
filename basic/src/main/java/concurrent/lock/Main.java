/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author csieflyman
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("========== ReadWriteLockReadWriteObject ===========");
        runTest(new ReadWriteLockReadWriteObject());
        System.out.println("========== StampedLockReadWriteObject ===========");
        runTest(new StampedLockReadWriteObject());
    }

    private static void runTest(ReadWriteObject rw) {
        List<Thread> readThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            readThreads.add(new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    rw.read();
                }
            }));
        }
        List<Thread> writeThreads = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            writeThreads.add(new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    rw.write(new String("test "));
                }
            }));
        }

        readThreads.stream().forEach(t -> t.start());
        writeThreads.stream().forEach(t -> t.start());
    }
}
