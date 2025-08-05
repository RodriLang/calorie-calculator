package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.List;
import java.util.UUID;

public record IngredientResponseDto(

        UUID id,

        FoodResponseDto food,

        Double quantity,

        MeasurementUnitResponseDto unit,

        List<RecipeResponseDto> recipes
) {
}
