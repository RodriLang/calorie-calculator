package com.trainerapp.calorie_calculator.model.dto;

public record MealCardDto(
        Long id,
        String name,
        String url,
        String difficulty,
        String preparationTime) {
}
