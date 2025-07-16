package com.trainerapp.calorie_calculator.dto.filter;

import java.util.List;

public record MealFilterParamsDto(
        List<Long> tagIds,
        String name,
        Double minCalories,
        Double maxCalories
) {
}

