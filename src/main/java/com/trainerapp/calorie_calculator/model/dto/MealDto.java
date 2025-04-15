package com.trainerapp.calorie_calculator.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MealDto(
        Long id,
        String name,
        String shortDescription,
        String url,
        List<RecipeDto> recipeList,
        List<TagDto> tagList) {
}
