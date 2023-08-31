package org.example.aop.assignment;

/**
 * @author = mbalaji on 31-08-2023
 * @project = spring-aop
 */
public abstract class Food{

    private double proteins;
    private double fats;
    private double carbs;
    private double tastyScore;
    private String type;

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getTastyScore() {
        return tastyScore;
    }

    public String getType() {
        return type;
    }

    public Food(double proteins, double fats, double carbs, double tastyScore, String type) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
        this.tastyScore = tastyScore;
        this.type = type;
    }

    abstract String getMacroNutrients();
}
