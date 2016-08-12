/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.threadpool;

/**
 * @author csieflyman
 */
public class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3);
        System.out.println("pool start...");
        pool.start();
        Client client = new Client(pool);
        System.out.println("client run...");
        client.run();
        System.out.println("client wait response");
        client.getResponse();
        System.out.println("pool shutdown...");
        pool.shutdown();
    }
}
