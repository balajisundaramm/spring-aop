package org.example.aop.streams;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author = mbalaji on 25-08-2023
 * @project = spring-aop
 */
public class StreamPrograms {
    public static void main(String[] args) {
//        iterate();
        /*System.out.println(calculateAverage());
        createIntStreams();*/
//        upperCase();
//        sumOfEven();
//        removeDuplicates();
//        sortBy();
//        findMaxAndMin();
        findSecondMinAndMax();
        new StreamPrograms().getSecondHighestSalaryName();
    }

    /**
     * Stream Iterate
     */
    private static void iterate() {
        Stream.iterate(1, ele -> ele + 1).limit(5).forEach(System.out::println);
    }

    /**
     * Write a Java program to calculate the average of a list of integers using streams.
     */
    private static double calculateAverage() {
        IntStream intStream = IntStream.of(1,2,5,7);
        List<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3));
        double v = ints.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
        return intStream.average().orElse(0);
    }

    private static void createIntStreams() {
        IntStream.range(0, 10).forEach(System.out::println);
        IntStream.rangeClosed(0, 10).forEach(System.out::println);
        IntStream.range(1,10).boxed().forEach(System.out::println);
    }

    private static void upperCase() {
        Stream<String> stream = Stream.of("Balaji", "Abhinaya", "Father", "Mother");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    private static void sumOfEven() {
        Stream<Integer> integerStream = Stream.of(1, 4, 6, 2, 7);
        integerStream.filter(i -> i%2==0).mapToInt(Integer::intValue).sum();
        int sum = IntStream.of(1, 4, 3, 6, 2).filter(i -> i % 2 == 0).sum();
        System.out.println(sum);
    }

    private static void removeDuplicates() {
        Stream<String> streams = Stream.of("Abhi", "Abhi", "Balaji");
        List<String> collect = streams.distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void sortBy() {
        List<String> list = new ArrayList<>(Arrays.asList("Balaji", "Abhi", "Dheerkkavan"));
        list.stream().sorted().forEach(System.out::println);
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    private static void findMaxAndMin() {
        Supplier<Stream<Integer>> integerStream = () -> Stream.of(1, 4, 6, 2, 7);
        System.out.println(integerStream.get().max(Integer::compareTo).orElse(0));
        System.out.println(integerStream.get().min(Integer::compareTo).orElse(0));
    }

    private static void findSecondMinAndMax() {
        Supplier<Stream<Integer>> integerStream = () -> Stream.of(1, 4, 6, 2, 7);
        System.out.println(integerStream.get().distinct().sorted().skip(1).findFirst());
        System.out.println(integerStream.get().distinct().sorted(Comparator.reverseOrder()).skip(2).max(Integer::compareTo));
    }

    private void getSecondHighestSalaryName() {
        Employee e = new Employee("Balaji", 50000);
        Employee e1 = new Employee("Abhi", 70000);
        Employee e2 = new Employee("Sandhiya", 80000);
        Employee e3 = new Employee("Dheerkkavan", 10000);
        List<Employee> list = new ArrayList<>();
        list.add(e);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        String s = list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1)
                .map(Employee::getName)
                .findFirst().orElse(null);
        System.out.println(s);
    }
}

class Employee {
    public String name;
    public double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

