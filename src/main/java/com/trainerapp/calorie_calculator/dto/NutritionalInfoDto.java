package com.trainerapp.calorie_calculator.dto;

import lombok.Builder;

@Builder
public record NutritionalInfoDto(
        NutrientValueDto energy,
        NutrientValueDto carbohydrates,
        NutrientValueDto sugars,
        NutrientValueDto protein,
        NutrientValueDto totalFat,
        NutrientValueDto saturatedFat,
        NutrientValueDto fiber) {
}
