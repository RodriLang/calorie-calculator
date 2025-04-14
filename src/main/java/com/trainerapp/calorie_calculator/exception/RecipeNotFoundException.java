package com.trainerapp.calorie_calculator.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(Long id) {
        super("Recipe not found with id: " + id);
    }
}
