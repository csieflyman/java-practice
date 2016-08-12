package generic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        testGenericStaticMethod();
        testArrayIsCovariant();
        testCollectionIsInvariant();
        testCollectionCoAndContravariance();
        testWildcardCovariantInSubTypingCube();
        testGenericTypeEqulas();
    }

    private static void testGenericStaticMethod() {
        String s1 = Main.staticMethod(new String("123"));
        Main main = new Main();
        main.instanceMethod(new String("123"));
    }

    private static <X> X staticMethod(X obj) {
        return obj;
    }

    private <T> T instanceMethod(T obj) {
        return obj;
    }

    private static void testArrayIsCovariant() {
        Animal[] animals = new Animal[2];
        Cat[] cats = new Cat[2];
        animals[0] = new Animal();
        animals[1] = new Cat(); // covariant
        animals = cats; // covariant
        try {
            animals[0] = new Dog();
        } catch (ArrayStoreException e) {
            System.out.println(" fool compiler: ");
        }

        //Object[] objArray = new int[2]; primitive array isn't extends Object[], instead of Object
        Object primitiveArray = new int[2];
        Object[] objArray = new Integer[2];
        Object obj = objArray;
    }

    private static void testCollectionIsInvariant() {
        List<?> l1 = new ArrayList<>();
        List<? extends Object> l2 = new ArrayList<>();
        List<Object> l3 = new ArrayList<>();
        List<String> l4 = new ArrayList<>();
        List l5 = new ArrayList();

        l1 = l2;
        l1 = l3;
        l1 = l4;
        l1 = l5;

        //l3 = l4; // compile error

        l5 = l1;
        l5 = l3;
        l5 = l4;
    }

    private static void testCollectionCoAndContravariance() {
        List<? extends Number> covariance = Arrays.asList(1, 2, 3);
        Number n = covariance.get(0); // readonly
        //covariance.add(Integer.valueOf(1)); can't write

        List<? super Number> contravariance = new ArrayList<Number>();
        contravariance.add(1L); // only-write
        // Number n2 = contravariance.get(0); // can't read

        // copy number
        for (Number number : covariance) {
            contravariance.add(number);
        }
    }

    private static void testWildcardCovariantInSubTypingCube() {
        List<Cat> l1 = new ArrayList<>();
        List<Animal> l2 = new ArrayList<>();
        List<? extends Cat> l3 = new ArrayList<>();
        List<? extends Animal> l4 = new ArrayList<>();
        Collection<Cat> c1 = new ArrayList<>();
        Collection<Animal> c2 = new ArrayList<>();
        Collection<? extends Cat> c3 = new ArrayList<>();
        Collection<? extends Animal> c4 = new ArrayList<>();

        l3 = l1;
        l4 = l1;
        l4 = l2;
        l4 = l3;

        c3 = c1;
        c4 = c1;
        c4 = c2;
        c4 = c3;

        c1 = l1;
        c2 = l2;
        c3 = l3;
        c4 = l3;
        c4 = l4;

        c1 = l1;
        c2 = l2;
        c3 = l3;
    }

    private static void testGenericTypeEqulas() {
        Node<Cat> cat1 = new Node<>(new Cat());
        Node<Cat> cat2 = new Node<>(new Cat());
        System.out.print(cat1.equals(cat2));
    }
}
