package com.trainerapp.calorie_calculator.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MealCardDto(
        Long id,
        String name,
        String url,
        String difficulty,
        String preparationTime,
        List<TagDto> tags) {
}
