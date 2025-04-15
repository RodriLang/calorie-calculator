package com.trainerapp.calorie_calculator.model.dto;

import lombok.Builder;

@Builder
public record NutrientValueDto(
        Double value,
        String unit) {
}
