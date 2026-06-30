package com.codealpha.fitnesstracker;

public class FitnessActivity {
    private String type;
    private int steps;
    private int calories;

    public FitnessActivity(String type, int steps, int calories) {
        this.type = type;
        this.steps = steps;
        this.calories = calories;
    }

    public String getType() { return type; }
    public int getSteps() { return steps; }
    public int getCalories() { return calories; }
}
