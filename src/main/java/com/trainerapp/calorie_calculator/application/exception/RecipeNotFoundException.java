package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String message) {
        super(message);
    }

    public RecipeNotFoundException(Long id) {
        super("Recipe not found with id: " + id);
    }

    public RecipeNotFoundException(UUID id) {
        super("Recipe not found with id: " + id);
    }
}
