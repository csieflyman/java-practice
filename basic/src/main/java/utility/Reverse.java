/*
 * Copyright (c) 2016 csieflyman. All rights reserved
 */

package utility;

/**
 * @author csieflyman
 */
public class Reverse {

    public static void main(String[] args) {
        System.out.println(reverseString("hello"));
        System.out.println(reverseString("h"));
    }

    public static String reverseString(String s) {
        char[] charArray = s.toCharArray();
        char[] resultArray = new char[charArray.length];
        for(int i = 0; i < charArray.length; i++) {
            resultArray[i] = charArray[charArray.length -1 - i];
        }
        return new String(resultArray);
    }
}
