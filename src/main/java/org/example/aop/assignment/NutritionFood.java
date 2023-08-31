package org.example.aop.assignment;

import java.util.Map;
import java.util.Scanner;

/**
 * @author = mbalaji on 31-08-2023
 * @project = spring-aop
 */
public class NutritionFood {

    public static void main(String[] args) {
        System.out.println("Enter number of food items");
        Scanner sc = new Scanner(System.in);
        int no = sc.nextInt();
        for (int i = 0; i < no; i++) {
            System.out.println("Enter the name of the food");
            sc = new Scanner(System.in);
            String name = sc.next();
            System.out.println("Enter the first method to invoke");
            sc = new Scanner(System.in);
            String param1 = sc.next();
            System.out.println("Enter the second method to invoke");
            sc = new Scanner(System.in);
            String param2 = sc.next();
            System.out.println("Enter the third method to invoke");
            sc = new Scanner(System.in);
            String param3 = sc.next();
            new NutritionFood().Test(name, param1, param2, param3);
        }
    }

    public void Test(String name, String param1, String param2, String param3) {
        Food food = (name.equalsIgnoreCase("egg"))
                ? new Egg(6.3, 5.3, 0.6, 7, "Non-Vegiterian")
                : new Bread(4.0, 1.1, 13.8, 8, "Vegetarian");
        String s = (param1.equalsIgnoreCase("getMacros")) ? food.getMacroNutrients()
                : (param1.equalsIgnoreCase("getType") ? name + " is " + food.getType()
                : String.valueOf("Taste : " +food.getTastyScore()));
        System.out.println(s);
        String s1 = (param2.equalsIgnoreCase("getMacros")) ? food.getMacroNutrients()
                : (param2.equalsIgnoreCase("getType") ? name + " is " + food.getType()
                : String.valueOf("Taste : " + food.getTastyScore()));
        System.out.println(s1);
        String s2 = (param3.equalsIgnoreCase("getMacros")) ? food.getMacroNutrients()
                : (param3.equalsIgnoreCase("getType") ? name + " is " + food.getType()
                : String.valueOf("Taste : " + food.getTastyScore()));
        System.out.println(s2);
    }
}

class Egg extends Food {

    public Egg(double proteins, double fats, double carbs, double tastyScore, String type) {
        super(proteins, fats, carbs, tastyScore, type);
    }

    @Override
    String getMacroNutrients() {
        return "An egg has " + this.getProteins() + " gms of protein, " +
                this.getFats() + " gms of fats and " + this.getCarbs() + " gms of carbohydrates.";
    }
}

class Bread extends Food {


    public Bread(double proteins, double fats, double carbs, double tastyScore, String type) {
        super(proteins, fats, carbs, tastyScore, type);
    }

    @Override
    String getMacroNutrients() {
        return "A slice of bread has " + this.getProteins() + " gms of protein, " +
                this.getFats() + " gms of fats and " + this.getCarbs() + " gms of carbohydrates.";
    }
}