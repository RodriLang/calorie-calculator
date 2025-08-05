package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class SeasoningNotFoundException extends RuntimeException {

    public SeasoningNotFoundException(String message) {
        super(message);
    }

    public SeasoningNotFoundException(Long id) {
        super("Seasoning not found with id: " + id);
    }

    public SeasoningNotFoundException(UUID id) {
        super("Seasoning not found with id: " + id);
    }
}
