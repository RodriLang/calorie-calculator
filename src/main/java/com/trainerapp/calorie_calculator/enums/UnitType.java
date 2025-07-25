package com.trainerapp.calorie_calculator.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UnitType {

    GRAMS("g"),
    MILLIGRAMS("mg"),
    KILOGRAMS("kg"),
    MICROGRAMS("μg"),
    MILLILITERS("ml"),
    UNIT("u"),
    TABLESPOON("cda"),  // Cucharada sopera
    TEASPOON("cdta"),   // Cucharada de té
    PERCENTAGE("%"),
    CUP("taza"),
    KILOCALORIES("kcal");

    private final String abbreviation;

    UnitType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static UnitType fromAbbreviation(String abbreviation) {
        return Arrays.stream(values())
                .filter(u -> u.abbreviation.equals(abbreviation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid unit abbreviation: " + abbreviation));
    }

}
