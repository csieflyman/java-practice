/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.guardedsuspension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

/**
 * @author csieflyman
 */
public class Client {

    private List<FutureTask> tasks = new CopyOnWriteArrayList<>();

    private ThreadPoolExecutor pool;

    private int threadCount = 10;

    public Client(ThreadPoolExecutor pool) {
        this.pool = pool;
        Function<Integer, Integer> function = a -> a * a;
        for (int i = 1; i <= threadCount; i++) {
            FutureTask task = new FutureTask(function, i);
            tasks.add(task);
        }
    }

    public void run() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            int taskIndex = i;
            threads.add(new Thread(() -> {
                pool.submit(tasks.get(taskIndex));
            }));
        }
        threads.stream().forEach(t -> t.start());
    }

    public void getResponse() {
        tasks.stream().forEach(task -> System.out.println("task result = " + task.get()));
    }
}
