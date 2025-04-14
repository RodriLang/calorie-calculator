package com.trainerapp.calorie_calculator.exception;

public class StepNotFoundException extends RuntimeException {
    public StepNotFoundException(String message) {
        super(message);
    }

    public StepNotFoundException(Long id) {
        super("Step not found with id: " + id);
    }
}
