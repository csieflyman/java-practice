package concurrent.masterwork;

import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

/**
 * @author flyman
 */
public class Worker<E, R> implements Runnable {

    private Queue<E> workQueue = null;

    private Map<E, R> resultMap = null;

    private Function<E, R> function;

    public Worker(Function<E, R> function) {
        this.function = function;
    }

    @Override
    public void run() {
        while(true) {
            E element = workQueue.poll();
            if(element == null) {
                break;
            }
            R result = function.apply(element);
            System.out.println("element = " + element + ", result = " + result);
            resultMap.put(element, result);
        }
    }

    public void setWorkQueue(Queue<E> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<E, R> resultMap) {
        this.resultMap = resultMap;
    }
}
