package com.trainerapp.calorie_calculator.dto;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import lombok.Builder;

import java.time.Duration;
import java.util.List;

@Builder
public record RecipeDto(
        Long id,
        String name,
        List<IngredientDto> ingredients,
        List<CustomIngredientDto> customIngredients,
        String shortDescription,
        List<String> steps,
        Duration preparationTime,
        DifficultyType difficulty) {
}
