package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(String message) {
        super(message);
    }

    public IngredientNotFoundException(Long id) {
        super("Ingredient not found with id: " + id);
    }

    public IngredientNotFoundException(UUID id) {
        super("Ingredient not found with id: " + id);
    }
}
