package com.trainerapp.calorie_calculator.web.dto.response;

public record NutritionalInfoResponseDto(
        NutrientValueResponseDto energy,
        NutrientValueResponseDto carbohydrates,
        NutrientValueResponseDto sugars,
        NutrientValueResponseDto protein,
        NutrientValueResponseDto totalFat,
        NutrientValueResponseDto saturatedFat,
        NutrientValueResponseDto fiber) {
}
