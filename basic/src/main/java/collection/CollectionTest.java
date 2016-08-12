package collection;

import java.util.*;

/**
 * @author flyman
 */
public class CollectionTest {

    public static void main(String args[]) {
        testList();
        testQueue();
        testMap();
    }

    private static void testList() {
        System.out.println("===== testList =====");
        List<String> sl = Arrays.asList("a","b","c","d","e");
        sl.sort((s1, s2) -> -s1.compareTo(s2));
        sl.forEach(System.out::print);
        System.out.print("\n");
    }

    private static void testQueue() {
        System.out.println("===== testQueue =====");
        Comparator<String> lengthComparator = (s1, s2) -> s2.length() - s1.length();
        lengthComparator.thenComparing((s1, s2) -> s1.charAt(0) - s2.charAt(0));
        PriorityQueue<String> queue = new PriorityQueue<>(lengthComparator);
        queue.offer("abc");
        queue.offer("de");
        queue.offer("f");
        queue.offer("bcd");
        while(queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }

    private static void testMap() {
        System.out.println("===== testMap =====");
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.remove("C", 3);
        map.replace("B", 2, 4);

        map.putIfAbsent("C", 8);
        map.merge("A", 1, (i1, i2) -> i1 + i2);
        map.put("D", null);
        System.out.println(map.getOrDefault("D", 16));
        map.putIfAbsent("D", 16);
        map.computeIfAbsent("E", (s) -> (int)s.charAt(0));
        map.putIfAbsent("F", 1);
        map.compute("F", (key, value) -> key.charAt(0) + value );
        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));

    }
}
