package com.trainerapp.calorie_calculator.exception;

public class MicronutrientNotFoundException extends RuntimeException {
    public MicronutrientNotFoundException(String message) {
        super(message);
    }
    public MicronutrientNotFoundException(Long id ) {
        super("Micronutrient not found with id: " + id);
    }
}
