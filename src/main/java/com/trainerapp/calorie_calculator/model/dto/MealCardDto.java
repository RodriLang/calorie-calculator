package com.trainerapp.calorie_calculator.model.dto;

import java.util.List;

public record MealDto(
        Long id,
        String name,
        String shortDescription,
        String url,
        List<RecipeDto> recipeList,
        List<TagDto> tagList) {
}
