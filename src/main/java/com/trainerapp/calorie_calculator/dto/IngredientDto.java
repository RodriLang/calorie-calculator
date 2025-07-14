package com.trainerapp.calorie_calculator.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record IngredientDto(
        FoodDto food,
        Double quantity,
        MeasurementUnitDto unit,
        List<RecipeDto> recipes) {
}
