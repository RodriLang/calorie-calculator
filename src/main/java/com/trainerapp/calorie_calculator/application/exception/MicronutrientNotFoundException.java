package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class MicronutrientNotFoundException extends RuntimeException {

    public MicronutrientNotFoundException(String message) {
        super(message);
    }

    public MicronutrientNotFoundException(Long id) {
        super("Micronutrient not found with id: " + id);
    }

    public MicronutrientNotFoundException(UUID id) {
        super("Micronutrient not found with id: " + id);
    }
}
