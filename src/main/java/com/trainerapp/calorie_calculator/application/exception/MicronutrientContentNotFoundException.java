package com.trainerapp.calorie_calculator.application.exception;

public class MicronutrientContentNotFoundException extends RuntimeException {
    public MicronutrientContentNotFoundException(String message) {
        super(message);
    }
    public MicronutrientContentNotFoundException( Long id) {
        super("Micronutrient content not found with id: " + id);
    }
}
