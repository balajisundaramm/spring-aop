package org.example.aop.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author = mbalaji on 25-08-2023
 * @project = spring-aop
 */
public class SortingStreams {
    public static void main(String[] args) {
        new SortingStreams().sort();
    }

    public void sort() {
        Employee e = new Employee("Balaji", 50000);
        Employee e1 = new Employee("Abhi", 70000);
        Employee e2 = new Employee("Sandhiya", 80000);
        Employee e3 = new Employee("Dheerkkavan", 10000);
        List<Employee> list = new ArrayList<>();
        list.add(e);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        /*
        Method 1
         */
        Collections.sort(list, Collections.reverseOrder(
                Comparator.comparingDouble(Employee::getSalary)));
        list.stream().map(Employee::getSalary).forEach(System.out::println);
        Collections.sort(list, Comparator.comparing(Employee::getName));
        list.stream().map(Employee::getName).forEach(System.out::println);

    }
}

