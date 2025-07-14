package com.trainerapp.calorie_calculator.dto;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import lombok.Builder;

@Builder
public record MicronutrientDto(
        Long id,
        String name,
        Double dailyAmount,
        String unit,
        MicronutrientType type) {
}
