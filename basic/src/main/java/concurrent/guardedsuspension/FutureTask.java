/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package concurrent.guardedsuspension;

import java.util.function.Function;

/**
 * @author csieflyman
 */
public class FutureTask {

    private Function<Integer, Integer> function;

    private Integer input;

    private Integer output;

    public Integer getInput() {
        return input;
    }

    private boolean isDone = false;

    public FutureTask(Function<Integer, Integer> function, Integer input) {
        this.function = function;
        this.input = input;
    }

    public synchronized void run(){
        if(isDone)
            return;
        output = function.apply(input);
        isDone = true;
        notifyAll();
    }

    public synchronized Integer get() {
        while(!isDone) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return output;
    }
}
