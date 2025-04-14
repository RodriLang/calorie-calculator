package com.trainerapp.calorie_calculator.model.dto.create;

public record IngredientDataDto(
        Long foodId,
        Double quantity,
        Long measurementUnitId) {
}

