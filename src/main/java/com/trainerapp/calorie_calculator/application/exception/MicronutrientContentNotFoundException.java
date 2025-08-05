package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class MicronutrientContentNotFoundException extends RuntimeException {

    public MicronutrientContentNotFoundException(String message) {
        super(message);
    }

    public MicronutrientContentNotFoundException(Long id) {
        super("Micronutrient content not found with id: " + id);
    }

    public MicronutrientContentNotFoundException(UUID id) {
        super("Micronutrient content not found with id: " + id);
    }
}
