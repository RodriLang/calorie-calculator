package com.trainerapp.calorie_calculator.application.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(Long id) {
        super("Step not found with id: " + id);
    }
}
