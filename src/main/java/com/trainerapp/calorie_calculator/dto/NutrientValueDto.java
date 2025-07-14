package com.trainerapp.calorie_calculator.dto;

import lombok.Builder;

@Builder
public record NutrientValueDto(
        Double value,
        String unit) {
}
