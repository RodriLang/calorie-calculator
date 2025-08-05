package com.trainerapp.calorie_calculator.application.exception;

import java.util.UUID;

public class SectionNotFoundException extends RuntimeException {

    public SectionNotFoundException(String message) {
        super(message);
    }

    public SectionNotFoundException(Long id) {
        super("Section not found with id: " + id);
    }

    public SectionNotFoundException(UUID id) {
        super("Section not found with id: " + id);
    }
}
