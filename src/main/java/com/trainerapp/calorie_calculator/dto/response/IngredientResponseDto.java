package com.trainerapp.calorie_calculator.dto.response;

import java.util.List;

public record IngredientResponseDto(
        FoodResponseDto food,
        Double quantity,
        MeasurementUnitResponseDto unit,
        List<RecipeResponseDto> recipes) {
}
