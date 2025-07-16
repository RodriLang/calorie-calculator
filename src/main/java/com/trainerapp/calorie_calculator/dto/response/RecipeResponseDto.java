package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import lombok.Builder;

import java.time.Duration;
import java.util.List;

@Builder
public record RecipeResponseDto(
        Long id,
        String name,
        List<IngredientResponseDto> ingredients,
        List<CustomIngredientResponseDto> customIngredients,
        String shortDescription,
        List<String> steps,
        Duration preparationTime,
        DifficultyType difficulty) {
}
