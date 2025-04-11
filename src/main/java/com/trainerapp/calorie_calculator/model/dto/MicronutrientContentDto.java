package com.trainerapp.calorie_calculator.model.dto;

public record MicronutrientContentDto(
        String micronutrientName,
        Double dailyPercentage,
        String unit) {
}
