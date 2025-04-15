package com.trainerapp.calorie_calculator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NutrientType {

    ENERGY("Energía", "kcal"),
    CARBOHYDRATES("Carbohidratos", "g"),
    SUGARS("Azúcares", "g"),
    PROTEIN("Proteínas", "g"),
    TOTAL_FAT("Grasas totales", "g"),
    SATURATED_FAT("Grasas saturadas", "g"),
    FIBER("Fibra", "g");

    private final String name;
    private final String unit;
}

