package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import lombok.Builder;

@Builder
public record MicronutrientContentResponseDto(
        String name,
        Double dailyPercentage,
        String unit,
        MicronutrientType type) {
}
