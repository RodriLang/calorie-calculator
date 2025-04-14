package com.trainerapp.calorie_calculator.model.dto.create;

public record CreateIngredientDto(
        Long foodId,
        Double quantity,
        CreateMeasurementUnitDto unit) {
}

