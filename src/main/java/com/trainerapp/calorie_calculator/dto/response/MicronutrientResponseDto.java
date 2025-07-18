package com.trainerapp.calorie_calculator.dto.response;

public record MicronutrientResponseDto(
        Long id,
        String name,
        Double dailyAmount,
        String unit,
        String type) {
}
