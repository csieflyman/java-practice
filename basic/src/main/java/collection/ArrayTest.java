package collection;

import java.lang.reflect.Array;

/**
 * @author flyman
 */
public class ArrayTest {

    public static void main(String[] args) {
        testArrayCopy();
        testArrayIsCovariant();
    }

    private static void testArrayCopy() {
        String[] stringArray = new String[]{"a", "b", "c"};
        //String[] stringArray1 = (String[]) arrayCopy1(stringArray); // ClassCastException
        Object[] stringArray1 = arrayCopy1(stringArray);
        String[] stringArray2 = genericArrayCopy1(stringArray, stringArray.length, String[].class);
        String[] stringArray3 = genericArrayCopy2(stringArray);
        System.out.println(equalArrays(stringArray1, stringArray2));
        System.out.println(equalArrays(stringArray1, stringArray3));
        System.out.println(equalArrays(stringArray2, stringArray3));
    }

    // only for copying object[]
    private static Object[] arrayCopy1(Object[] original) {
        Object[] copied = new Object[original.length];
        for (int i = 0; i < original.length; i++) {
            copied[i] = original[i];
        }
        return copied;
    }

    // source code from java.util.Arrays
    private static <T,U> T[] genericArrayCopy1(U[] original, int newLength, Class<? extends T[]> newType) {
        @SuppressWarnings("unchecked")
        T[] copy = ((Object)newType == (Object)Object[].class)
                ? (T[]) new Object[newLength]
                : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    // use generic
    private static <T> T[] genericArrayCopy2(T[] original) {
        Class arrayType = original.getClass().getComponentType();
        T[] copy = (T[]) Array.newInstance(arrayType, original.length);
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }

    private static <T> boolean equalArrays(T[] a1, T[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if(!a1[i].equals(a2[i])) {
                return false;
            }
        }
        return true;
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
        //Integer[] a = new int[2]; //
    }
}
