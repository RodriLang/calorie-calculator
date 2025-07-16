package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import lombok.Builder;

@Builder
public record MicronutrientResponseDto(
        Long id,
        String name,
        Double dailyAmount,
        String unit,
        MicronutrientType type) {
}
