package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import lombok.Builder;

import java.util.List;

@Builder
public record RecipeSectionDto(
        Long id,
        String name,
        List<IngredientDto> ingredients,
        List<SeasoningDto> seasonings,
        String description,
        List<String> steps) {
}
