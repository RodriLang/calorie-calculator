package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MealCardResponseDto(
        Long id,
        String name,
        String url,
        String difficulty,
        String preparationTime,
        List<TagResponseDto> tags) {
}
