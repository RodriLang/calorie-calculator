package com.trainerapp.calorie_calculator.exception;

public class RecipeSectionNotFoundException extends RuntimeException {
    public RecipeSectionNotFoundException(String message) {
        super(message);
    }

    public RecipeSectionNotFoundException(Long id) {
        super("RecipeSection not found with id: " + id);
    }
}
