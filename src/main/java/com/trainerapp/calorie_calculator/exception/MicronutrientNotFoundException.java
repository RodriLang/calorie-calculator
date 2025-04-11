package com.trainerapp.calorie_calculator.exception;

public class MicronutrientNotFoundException extends RuntimeException {
    public MicronutrientNotFoundException(String message) {
        super(message);
    }
}
