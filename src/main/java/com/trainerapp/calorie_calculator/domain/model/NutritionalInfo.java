package com.trainerapp.calorie_calculator.domain.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class NutritionalInfo {

    private Double energyValue = 0.0; // kcal

    private Double carbohydrates = 0.0; // g

    private Double sugars = 0.0; // g

    private Double protein = 0.0; // g

    private Double totalFat = 0.0; // g

    private Double saturatedFat = 0.0; // g

    private Double fiber = 0.0; // g
}