package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import com.trainerapp.calorie_calculator.enums.FlavorType;
import lombok.Builder;

import java.util.List;

@Builder
public record RecipeDto(
        Long id,
        String name,
        String description,
        String url,
        List<RecipeSectionDto> recipeList,
        String preparationTime,
        DifficultyType difficulty,
        List<TagDto> tagList,
        FlavorType flavorType) {
}
