package com.trainerapp.calorie_calculator.model.dto;

import java.util.List;

public record IngredientDto(
        FoodDto food,
        Double quantity,
        MeasurementUnitDto unit,
        List<RecipeDto> recipes) {
}
