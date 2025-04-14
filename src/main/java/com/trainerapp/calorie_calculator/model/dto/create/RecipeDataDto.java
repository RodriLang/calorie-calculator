package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.DifficultyType;

import java.util.List;

public record CreateRecipeDto(
        String name,
        List<CreateIngredientDto> ingredients,
        List<CreateCustomIngredientDto> customIngredients,
        String shortDescription,
        List<String> steps,
        String preparationTime,
        DifficultyType difficulty) {
}