package com.trainerapp.calorie_calculator.model.dto;

public record NutritionalInfoDto(
        NutrientValueDto energy,
        NutrientValueDto carbohydrates,
        NutrientValueDto sugars,
        NutrientValueDto protein,
        NutrientValueDto totalFat,
        NutrientValueDto saturatedFat,
        NutrientValueDto fiber) {
}
