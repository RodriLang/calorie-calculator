package com.trainerapp.calorie_calculator.exception;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(String message) {
        super(message);
    }

    public SectionNotFoundException(Long id) {
        super("Section not found with id: " + id);
    }
}
