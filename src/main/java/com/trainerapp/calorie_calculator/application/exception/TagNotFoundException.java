package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class TagNotFoundException extends RuntimeException {

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(Long id) {
        super("Step not found with id: " + id);
    }

    public TagNotFoundException(UUID id) {
        super("Step not found with id: " + id);
    }
}
