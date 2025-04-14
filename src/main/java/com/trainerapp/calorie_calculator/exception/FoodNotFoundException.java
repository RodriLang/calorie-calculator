package com.trainerapp.calorie_calculator.exception;

public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(String message) {
        super(message);
    }
    public FoodNotFoundException(Long id) {
        super("Food not found with id: " + id);
    }
}
