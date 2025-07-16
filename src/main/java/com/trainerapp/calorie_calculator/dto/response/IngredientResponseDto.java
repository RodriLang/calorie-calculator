package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record IngredientResponseDto(
        FoodResponseDto food,
        Double quantity,
        MeasurementUnitResponseDto unit,
        List<RecipeResponseDto> recipes) {
}
