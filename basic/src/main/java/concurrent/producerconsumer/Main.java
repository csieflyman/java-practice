package concurrent.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        //testSynchronizedMethod();
        //testAsync();
        //testAsyncCondition();
        testBlockingQueue();
    }

    private static void testSynchronizedMethod() {
        System.out.println("========== Test SynchronizedMethod ==========");
        runTest(new SynchronizedMethodFactory());
    }

    private static void testAsync() {
        System.out.println("========== Test SynchronizedBlock ==========");
        runTest(new SynchronizedBlockFactory());
    }

    private static void testAsyncCondition() {
        System.out.println("========== Test Condition ==========");
        runTest(new ConditionFactory());
    }

    private static void runTest(Factory factory) {
        int total = 10;
        int producerThreadCount = 3;
        int consumerThreadCount = 3;

        List<Thread> producerThreads = new ArrayList<>();
        for (int i = 0; i < producerThreadCount ; i++) {
            producerThreads.add(new Thread(new Producer(factory, total)));
        }
        List<Thread> consumerThreads = new ArrayList<>();
        for (int i = 0; i < consumerThreadCount ; i++) {
            consumerThreads.add(new Thread(new Consumer(factory, total)));
        }
        producerThreads.stream().forEach(t -> t.start());
        consumerThreads.stream().forEach(t -> t.start());
    }

    private static void testBlockingQueue() {
        System.out.println("========== testBlockingQueue ==========");
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

        int total = 10;
        int producerThreadCount = 3;
        int consumerThreadCount = 3;

        List<Thread> producerThreads = new ArrayList<>();
        for (int i = 0; i < producerThreadCount ; i++) {
            producerThreads.add(new Thread(() -> {
                for (int j = 0; j < total; j++) {
                    try {
                        queue.put(Integer.valueOf(j));
                        System.out.println("put " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        List<Thread> consumerThreads = new ArrayList<>();
        for (int i = 0; i < consumerThreadCount ; i++) {
            consumerThreads.add(new Thread(() -> {
                for (int j = 0; j < total; j++) {
                    Integer result = queue.poll();
                    System.out.println("take " + result);
                }

            }));
        }
        producerThreads.stream().forEach(t -> t.start());
        consumerThreads.stream().forEach(t -> t.start());
    }
}

