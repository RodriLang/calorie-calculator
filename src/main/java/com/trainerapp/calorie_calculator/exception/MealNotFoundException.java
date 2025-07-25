package com.trainerapp.calorie_calculator.exception;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message) {
        super(message);
    }
    public MealNotFoundException(Long id) {
        super("Meal not found with id: " + id);
    }
}
