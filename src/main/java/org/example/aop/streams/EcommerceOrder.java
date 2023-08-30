package org.example.aop.streams;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author = mbalaji on 30-08-2023
 * @project = spring-aop
 */
public class EcommerceOrder {

    public static void main(String[] args) {
        List<String> users = new ArrayList<>(Arrays.asList("1,user1,120", "2,user2,1000"));
        List<String> orders = new ArrayList<>(Arrays.asList("1,1|5,2|2", "2,2|1,3|3,1|2"));
        List<String> products = new ArrayList<>(Arrays.asList("1,product1,20,2,20", "2,product2,30,1,10","3,product3,25,3,60"));
        Map<String, Integer> stock = new EcommerceOrder().getStock(users, products, orders);
        stock.entrySet().stream().forEach(stringIntegerEntry -> {
            System.out.println(stringIntegerEntry.getKey());
            System.out.println(stringIntegerEntry.getValue());
        });
    }

    public Map<String, Integer> getStock(List<String> users, List<String> products, List<String> orderString) {
        Map<String, Integer> result = new HashMap<>();
        List<User> userList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Map<String, List<Order>> orders = new HashMap<>();
        users.stream().forEach(s -> {
            String[] split = s.split(",");
            userList.add(new User(split[0], split[1], Integer.parseInt(split[2])));
        });
        products.stream().forEach(s -> {
            String[] split = s.split(",");
            productList.add(new Product(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4])));
        });
        orderString.stream().forEach(s -> {
            String[] split = s.split(",");
            for (int i = 1; i < split.length; i++) {
                String[] details = split[i].split("\\|");
                List<Order> orders1 = orders.get(split[0]);
                List<Order> list = Arrays.asList(new Order(details[0], Integer.parseInt(details[1])));
                if (CollectionUtils.isEmpty(orders1)) {
                    orders.put(split[0], new ArrayList<>(list));
                } else {
                    orders1.addAll(list);
                    orders.put(split[0], orders1);
                }
            }
        });

        orders.entrySet().forEach(stringListEntry -> {
            AtomicInteger sum = new AtomicInteger(0);
            final int[] maxSC = {0};
            stringListEntry.getValue().stream().forEach(order -> {
                Product product = productList.stream().filter(p -> p.getId().equalsIgnoreCase(order.getProductId())).findFirst().get();
                if (product.getQuantity() >= order.getQuantity()) {
                    sum.addAndGet(calculatePrice(order.getQuantity(), product.getPrice()));
                    maxSC[0] = maxSC[0] > product.getShippingCost() ? maxSC[0] : product.getShippingCost();
                }
            });
            User user = userList.stream().filter(u -> u.getUserId().equalsIgnoreCase(stringListEntry.getKey())).findFirst().get();
            if (user.getBalance() >= sum.get() + maxSC[0]) {
                stringListEntry.getValue().stream().forEach(order -> {
                    Product product = productList.stream().filter(p -> p.getId().equalsIgnoreCase(order.getProductId())).findFirst().get();
                    result.put(product.getName(), product.getQuantity() - order.getQuantity());
                    user.setBalance(user.getBalance() - sum.get() + maxSC[0]);
                });
            }
        });
        System.out.println("Users");
        users.stream().forEach(System.out::println);
        return result;
    }

    public static boolean checkStock(String product, int quantity) {
        return false;
    }

    public static int calculatePrice(int quantity, int price) {
        return quantity * price;
    }
}

class User {
    private String userId;
    private String userName;
    private int balance;

    public User(String userId, String userName, int balance) {
        this.userId = userId;
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

class Product {
    private String id;
    private String name;
    private int price;
    private int shippingCost;

    private int quantity;

    public Product(String id, String name, int price, int shippingCost, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shippingCost = shippingCost;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(int shippingCost) {
        this.shippingCost = shippingCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Order {
    private String productId;
    private int quantity;

    public Order(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}