package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class StepNotFoundException extends RuntimeException {

    public StepNotFoundException(String message) {
        super(message);
    }

    public StepNotFoundException(Long id) {
        super("Step not found with id: " + id);
    }

    public StepNotFoundException(UUID id) {
        super("Step not found with id: " + id);
    }
}
