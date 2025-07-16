package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MealResponseDto(
        Long id,
        String name,
        String shortDescription,
        String url,
        List<RecipeResponseDto> recipeList,
        List<TagResponseDto> tagList) {
}
