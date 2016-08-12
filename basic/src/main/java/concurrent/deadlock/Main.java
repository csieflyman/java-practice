/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.deadlock;

/**
 * @author csieflyman
 */
public class Main {

    public static void main(String[] args) {
        DeadLockResource r1 = new DeadLockResource();
        DeadLockResource r2 = new DeadLockResource();
        //runTest(r1, r2);

        NoDeadLockResource nr1 = new NoDeadLockResource("r1");
        NoDeadLockResource nr2 = new NoDeadLockResource("r2");
        runTest(nr1, nr2);
    }

    private static void runTest(Resource r1, Resource r2) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                r1.cooperate(r2);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                r2.cooperate(r1);
            }
        });

        t1.start();
        t2.start();
    }
}
