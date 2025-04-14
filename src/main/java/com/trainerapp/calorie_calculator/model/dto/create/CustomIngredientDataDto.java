package com.trainerapp.calorie_calculator.model.dto.create;

public record CreateCustomIngredientDto(
        String food,
        Double quantity,
        CreateMeasurementUnitDto unit,
        String note) {
}
