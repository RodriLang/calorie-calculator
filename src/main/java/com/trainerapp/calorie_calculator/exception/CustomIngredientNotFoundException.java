package com.trainerapp.calorie_calculator.exception;

public class CustomIngredientNotFoundException extends RuntimeException {
    public CustomIngredientNotFoundException(String message) {
        super(message);
    }
    public CustomIngredientNotFoundException(Long id) {
        super("Custom Ingredient not found with id: " + id);
    }
}
