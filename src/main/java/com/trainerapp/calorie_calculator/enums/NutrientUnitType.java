package com.trainerapp.calorie_calculator.enums;

public enum NutrientUnitType {
    CALORIES("kcal"),
    GRAMS("g");

    private final String unit;

    NutrientUnitType(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
