package com.trainerapp.calorie_calculator.application.exception;

public class MeasurementUnitNotFoundException extends RuntimeException {
    public MeasurementUnitNotFoundException(String message) {
        super(message);
    }
    public MeasurementUnitNotFoundException( Long id) {
        super("Measurement unit not found with id: " + id);
    }
}
