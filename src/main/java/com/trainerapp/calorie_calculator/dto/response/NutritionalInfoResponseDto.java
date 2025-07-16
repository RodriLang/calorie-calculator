package com.trainerapp.calorie_calculator.dto.response;

import lombok.Builder;

@Builder
public record NutritionalInfoResponseDto(
        NutrientValueResponseDto energy,
        NutrientValueResponseDto carbohydrates,
        NutrientValueResponseDto sugars,
        NutrientValueResponseDto protein,
        NutrientValueResponseDto totalFat,
        NutrientValueResponseDto saturatedFat,
        NutrientValueResponseDto fiber) {
}
