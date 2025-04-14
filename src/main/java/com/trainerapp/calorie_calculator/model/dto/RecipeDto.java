package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.DifficultyType;

import java.time.Duration;
import java.util.List;

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
