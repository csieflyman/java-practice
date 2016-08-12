package concurrent.future;

import java.util.concurrent.Callable;

/**
 * @author flyman
 */
public class Future<R> {

    private boolean isDone = false;

    private R result;

    public synchronized void submit(Callable<R> task) {
        if(isDone) {
            return;
        }
        try {
            result = task.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isDone = true;
        notify();
    }

    public synchronized R get() throws InterruptedException {
        while(!isDone) {
            wait();
        }
        return result;
    }
}
