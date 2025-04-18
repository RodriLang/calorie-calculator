package com.trainerapp.calorie_calculator.exception;

public class SeasoningNotFoundException extends RuntimeException {
    public SeasoningNotFoundException(String message) {
        super(message);
    }
    public SeasoningNotFoundException(Long id) {
        super("Custom Ingredient not found with id: " + id);
    }
}
