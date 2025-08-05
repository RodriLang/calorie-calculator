package com.trainerapp.calorie_calculator.web.dto.response;

public record MicronutrientContentResponseDto(
        String name,
        Double dailyPercentage,
        String unit,
        String type) {
}
