package concurrent.future;

import java.util.concurrent.Callable;

/**
 * @author flyman
 */
public class LongWork<T> implements Callable<T> {

    private int consumeTime = 3000;
    private T result;

    public LongWork(int consumeTime, T result) {
        this.consumeTime = consumeTime;
        this.result = result;
    }

    @Override
    public T call() {
        System.out.println("Start LongWork...");
        try {
            Thread.sleep(consumeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish LongWork...");
        return result;
    }
}
