package org.example.aop.annotations;

import java.util.Arrays;
import java.util.List;

/**
 * @author = mbalaji on 24-08-2023
 * @project = spring-aop
 */
public class SafeVarArgsTest {
    public static void main(String[] args) {
        SafeVarArgsTest test = new SafeVarArgsTest();

        test.printString("String1", "String2");

        test.printStrings("String1", "String2");

        List<String> testStringList1 = Arrays.asList("One", "Two");
        List<String> testStringList2 = Arrays.asList("Three", "Four");

        test.printStringSafeVarArgs(testStringList1, testStringList2);
    }

    private void printString(String s1, String s2) {
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * Create a single method to handle optional parameters
     * @param s
     */
    private void printStrings(String... s) {
        for (String s1: s) {
            System.out.println(s1);
        }
    }

    @SafeVarargs
    private void printStringSafeVarArgs(List<String>... lists) {
        for (List<String> testStringList : lists) {
            for (String testString : testStringList) {
                System.out.println(testString);
            }
        }
    }

}
