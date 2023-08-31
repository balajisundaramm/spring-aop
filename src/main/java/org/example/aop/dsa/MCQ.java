package org.example.aop.dsa;

/**
 * @author = mbalaji on 31-08-2023
 * @project = spring-aop
 */
public class MCQ {
    public static void main(String[] args) {
        checkDouble();
    }

    static void checkDouble() {
        double d = 10.0 / -0;
        System.out.println(d);
        if(d == Double.POSITIVE_INFINITY)
            System.out.println("Positive infinity");
        else
            System.out.println("Negative infinity");
    }
}
