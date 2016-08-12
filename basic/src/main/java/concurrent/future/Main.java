package concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        testJDKFeature();
        testMyFuture();
    }

    private static void testMyFuture() {
        Future<String> future = new Future();
        future.submit(new LongWork(4000, "abc"));
        doOtherThings();
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
    }

    private static void testJDKFeature() {
        FutureTask<String> futureTask = new FutureTask(new LongWork(2000, "abc"));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(futureTask);
        doOtherThings();
        String result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        executorService.shutdown();

    }

    private static void doOtherThings() {
        System.out.println("Start doOtherThings...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish doOtherThings...");
    }
}
