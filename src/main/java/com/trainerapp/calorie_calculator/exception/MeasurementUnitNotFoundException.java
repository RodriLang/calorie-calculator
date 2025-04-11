package com.trainerapp.calorie_calculator.exception;

public class MeasurementUnitNotFoundException extends RuntimeException {
    public MeasurementUnitNotFoundException(String message) {
        super(message);
    }
}
