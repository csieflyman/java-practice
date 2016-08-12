package concurrent.masterwork;

import java.util.function.BinaryOperator;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        Worker<Integer, Integer> worker = new Worker<>(a -> a * a);
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        Master<Integer, Integer> master = new Master(worker, 3, binaryOperator);
        for (int i = 1; i <= 100 ; i++) {
            master.submit(i);
        }
        System.out.println("Start execute...");
        master.execute();
        System.out.println("result = " + master.getResult());

    }
}
