/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package autoboxing;

/**
 * @author csieflyman
 */
public class Main {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 123;
        Integer f = 123;
        Long g = 3L;
        System.out.println(c == d);//T
        System.out.println(e == f);//T
        System.out.println(c == (a+b));//T
        System.out.println(c.equals((a + b)));//T
        System.out.println(g == (a + b));//T
        System.out.println(g.equals((a + b)));//F

    }
}
