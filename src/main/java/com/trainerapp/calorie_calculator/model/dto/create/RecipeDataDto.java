package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.DifficultyType;

import java.time.Duration;
import java.util.List;

public record RecipeDataDto(
        String name,
        List<IngredientDataDto> ingredients,
        List<CustomIngredientDataDto> customIngredients,
        String shortDescription,
        List<String> steps,
        Duration preparationTime,
        DifficultyType difficulty) {
}