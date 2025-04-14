package com.trainerapp.calorie_calculator.model.dto.create;

import java.util.List;

public record CreateMealDto(
        String name,
        String shortDescription,
        String url,
        List<Long> recipeIds,
        List<Long> tagIds) {
}

