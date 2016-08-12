package concurrent.masterwork;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author flyman
 */
public class Master<E, R> {

    private Queue<E> workQueue = new ConcurrentLinkedQueue<>();

    private Set<Thread> threadSet = new HashSet<>();

    private Map<E, R> resultMap = new ConcurrentHashMap<>();

    private BinaryOperator<R> reducer;

    public Master(Worker worker, int workerCount, BinaryOperator<R> reducer) {
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for (int i = 0; i < workerCount; i++) {
            threadSet.add(new Thread(worker, Integer.toString(i)));
        }
        this.reducer = reducer;
    }

    public void submit(E element) {
        workQueue.add(element);
    }

    public void execute() {
        threadSet.forEach(t -> t.start());
    }

    public boolean isDone() {
        return threadSet.stream().allMatch(t -> t.getState() == Thread.State.TERMINATED);
    }

    public R getResult() {
        while(true) {
            if(isDone()) {
                break;
            }
        }

        if (reducer != null) {
            Optional<R> result = resultMap.values().stream().reduce(reducer);
            return result.get();
        }
        return null;
    }
}
