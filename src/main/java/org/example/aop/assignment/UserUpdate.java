package org.example.aop.assignment;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author = mbalaji on 31-08-2023
 * @project = spring-aop
 */
public class UserUpdate {
    public static void main(String[] args) {
        System.out.println("Enter no of users in the db");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the " + i + " user's Props comma separated");
            sc = new Scanner(System.in);
            String user = sc.nextLine();
            users.add(user);
        }
        System.out.println("Enter no of users in for the operation");
        sc = new Scanner(System.in);
        int m = sc.nextInt();
        List<String> operations = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            System.out.println("Enter the " + i + " user's Props comma separated");
            sc = new Scanner(System.in);
            String user = sc.nextLine();
            operations.add(user);
        }
        List<String> test = test(users, operations);
        test.stream().forEach(System.out::println);
    }

    static List<String> test(List<String> db, List<String> operations) {
        List<String> result = new ArrayList<>();
        AtomicInteger updated = new AtomicInteger();
        AtomicInteger created = new AtomicInteger();
        Map<String, String> userMap = createUserMap(db);
        operations.stream().forEach(s -> {
            if(s.startsWith("0")) {
                created.set(created.get() + 1);
            } else {
                String s1 = userMap.get(s.substring(0, 1));
                if(!s1.equalsIgnoreCase(s)) {
                    updated.set(updated.get() + 1);
                }
            }
        });
        result.add("Updated : " + updated.get());
        result.add("Inserted : " + created.get());
        return result;
    }

    static Map<String, String> createUserMap(List<String> users) {
        Map<String, String> map = new HashMap<>();
        users.stream().forEach(s -> {
            if (!s.startsWith("0"))
                map.put(s.substring(0, 1), s);
        });
        return map;
    }
}

class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
