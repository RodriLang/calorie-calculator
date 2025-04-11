package com.trainerapp.calorie_calculator.model.dto;

public record CreateNutritionalInfoDto(
        double energy,
        double carbohydrates,
        double sugars,
        double protein,
        double totalFat,
        double saturatedFat,
        double fiber
) {}