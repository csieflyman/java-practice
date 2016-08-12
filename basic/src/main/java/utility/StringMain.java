package utility;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author flyman
 */
public class StringMain {

    public static void main(String[] args) {
        System.out.println(String.join("-", "a", "b", "c"));
        System.out.println(String.join("-", Arrays.asList("a", "b", "c")));
        StringJoiner joiner = new StringJoiner("-");
        System.out.println(joiner.add("a").add("b").add("c").toString());
        Arrays.asList("a", "b", "c").stream().collect(Collectors.joining("-"));
    }
}
