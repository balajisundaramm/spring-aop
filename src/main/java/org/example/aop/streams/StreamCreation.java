package org.example.aop.streams;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author = mbalaji on 25-08-2023
 * @project = spring-aop
 */
public class StreamCreation {
    public static void main(String[] args) {
        /*createStreamFromCollection();
        createStreamFromValues();
        createStreamFromArray();
        createFromBuilder();*/
//        createUsingIterator(2, 5);
//        createUsingGenerator();
        createUsingPatternAndPredicate();
    }

    public static void createStreamFromCollection() {
        List<String> list = new ArrayList<>();
        list.add("Johny");
        list.add("Stella");
        list.add("David");
        list.add("Amy");
        Stream<String> stream = list.stream();
        Iterator<String> iterator = stream.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void createStreamFromValues() {
        Stream<String> stream = Stream.of("Gopi", "Sudha", "Muthu", "Leela");
        stream.forEach(System.out::println);
    }

    public static void createStreamFromArray() {
        String[] strings = new String[]{"Gopi", "Sudha", "Muthu", "Leela"};
        Stream<String> strings1 = Stream.of(strings);
        strings1.forEach(System.out::println);
        Stream<String> streamed = Arrays.stream(strings);
        streamed.forEach(System.out::println);
    }

    public static void createFromBuilder() {
        /**
         * Create Empty stream
         */
        Stream<Object> emptyStream = Stream.empty();
        Stream.Builder<Integer> builder = Stream.builder();
        Stream<Integer> build = builder.add(1).add(2).add(3).build();
        build.forEach(System.out::println);
    }

    /**
     * Create square values of stream n no of times
     * resulting stream is infinite
     * @param seedValue
     * @param limit
     */
    public static void createUsingIterator(int seedValue, int limit) {
        /*
        no of times
         */
        Stream<Integer> iterate = Stream.iterate(seedValue, (Integer n) -> n * n).limit(limit);
        iterate.forEach(System.out::println);
        /*
        Upto a number
         */
        Stream.iterate(seedValue, n -> n<=256, n -> n * n)
                .forEach(System.out::println);
    }

    /**
     * Generator accepts supplier
     * resulting stream is infinite so set limit otherwise it will run till max memory
     */
    public static void createUsingGenerator() {
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    public static void createUsingPatternAndPredicate() {
        List<String> list = new ArrayList<>(Arrays.asList("Balaji", "Abhi", "Dheerkkavan", "Diamond"));
        Pattern pattern = Pattern.compile("^D");
        list.stream().filter(pattern.asPredicate()).forEach(System.out::println);
    }

    public static void createStreamsOf() {
        String[] strings = new String[]{"Gopi", "Sudha", "Muthu", "Leela"};
        Stream<String> strings1 = Stream.of(strings);
        IntStream intStream = IntStream.of(new int[]{1, 2, 3, 4, 5});
    }
}
