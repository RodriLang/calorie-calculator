package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import lombok.Builder;

@Builder
public record MicronutrientContentDto(
        String name,
        Double dailyPercentage,
        String unit,
        MicronutrientType type) {
}
