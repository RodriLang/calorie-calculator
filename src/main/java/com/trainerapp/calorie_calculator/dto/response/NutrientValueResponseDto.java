package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

@Builder
public record NutrientValueResponseDto(
        Double value,
        String unit) {
}
