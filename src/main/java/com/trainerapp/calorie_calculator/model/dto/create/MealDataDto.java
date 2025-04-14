package com.trainerapp.calorie_calculator.model.dto.create;

import java.util.List;

public record MealDataDto(
        String name,
        String shortDescription,
        String url,
        List<Long> recipeIds,
        List<TagDataDto> tags) {
}

