package lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        System.out.println(numbers.stream().map(i -> i + 1).filter(i -> i%2 == 0).reduce(0, (result, i) -> result + i));
        System.out.println(numbers.stream());


    }
}
