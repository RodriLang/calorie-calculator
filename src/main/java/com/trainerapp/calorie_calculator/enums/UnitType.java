package com.trainerapp.calorie_calculator.enums;

import lombok.Getter;

@Getter
public enum UnitType {

    GRAMS("g"),
    MILLIGRAMS("mg"),
    KILOGRAMS("kg"),
    MICROGRAMS("μg"),
    MILLILITERS("ml"),
    UNIT("u"),
    TABLESPOON("cda"),  // Cucharada sopera
    TEASPOON("cdta");   // Cucharada de té

    private final String abbreviation;

    UnitType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
