package org.example.aop.dsa;


import java.util.*;
import java.util.regex.Pattern;

/**
 * @author = mbalaji on 30-08-2023
 * @project = spring-aop
 */
public class BankChatBot {

    public static void main(String[] args) {
        Map<String, List<String>> keys = new HashMap<>();
        keys.put("SHOW", new ArrayList<>(Arrays.asList("see", "show")));
        keys.put("DEPOSIT", new ArrayList<>(Arrays.asList("deposit", "put", "invest", "transfer")));
        keys.put("WITHDRAW", new ArrayList<>(Arrays.asList("withdraw", "pull")));
        String input = "";
        double amount = 0;
        double balance = 0;
        int ch = 1;
        while (ch != 0) {
            System.out.println("Enter the no");
            Scanner sc1 = new Scanner(System.in);
            if(sc1.hasNextInt()) {
                int no = sc1.nextInt();
                sc1 = new Scanner(System.in);
                for (int j = 0; j < no; j++) {
                    boolean flag = true;
                    while (flag) {
                        System.out.println("Enter the command");
                        String key = sc1.nextLine();
                        sc1 = new Scanner(System.in);
                        if (key == null && key.trim() == "") {
                            System.out.println("Sorry, Not in a keywords list");
                        }
                        for (Map.Entry<String, List<String>> stringListEntry : keys.entrySet()) {
                            Optional<String> first = stringListEntry.getValue().stream().filter(s -> {
                                return key.toLowerCase().contains(s);
                            }).findFirst();
                            if (first.isPresent()) {
                                input = stringListEntry.getKey();
                                flag = false;
                                break;
                            }
                        }
                        if (input.equalsIgnoreCase("")) {
                            System.out.println("Keyword is not present");
                        }
                        System.out.println("Key " + input);
                        if (!input.equalsIgnoreCase("SHOW")) {
                            String[] split = key.split(" ");
                            for (int i = 0; i < split.length; i++) {
                                Pattern compile = Pattern.compile("\\d|\\d\\.\\d");
                                if (compile.matcher(split[i]).find()) {
                                    amount = Double.parseDouble(split[i]);
                                    ch = 0;
                                }
                            }
                            if (amount == 0) {
                                flag = true;
                                System.out.println("amount is not present");
                            }
                        } else {
                            ch = 0;
                        }
                    }
                    System.out.println("Key " + input + " amount " + amount);
                    switch (input) {
                        case "DEPOSIT":
                            balance = add(amount, balance);
                            break;
                        case "WITHDRAW":
                            balance = withdraw(amount, balance);
                            break;
                        default:
                            balance = balance;
                    }
                    input = "";
                    System.out.println("balance " + balance);
                }
            }
        }

    }

    public static double add(double amount, double balance) {
        balance += amount;
        return balance;
    }

    public static double withdraw(double amount, double balance) {
        if(balance !=0) {
            if (balance >= amount) {
                balance = balance - amount;
            } else {
                System.out.println("Unable to withdraw amount is bigger");
            }
        }
        return balance;
    }

}
