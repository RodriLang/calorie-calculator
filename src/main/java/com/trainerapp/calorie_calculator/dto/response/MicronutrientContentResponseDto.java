package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;

public record MicronutrientContentResponseDto(
        String name,
        Double dailyPercentage,
        String unit,
        String type) {
}
