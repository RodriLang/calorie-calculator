package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.List;

public record MealCardResponseDto(
        Long id,
        String name,
        String url,
        String difficulty,
        String preparationTime,
        List<TagResponseDto> tags) {
}
