package com.trainerapp.calorie_calculator.model.dto.create;

public record NutritionalInfoDataDto(
        Double energy,
        Double carbohydrates,
        Double sugars,
        Double protein,
        Double totalFat,
        Double saturatedFat,
        Double fiber) {
}