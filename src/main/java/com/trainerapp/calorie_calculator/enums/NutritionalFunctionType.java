package com.trainerapp.calorie_calculator.enums;

import lombok.Getter;

@Getter
public enum NutritionalFunctionType {
    ENERGY_FOOD("Alimento energético"),
    REGULATORY_FOOD("Alimento regulador"),
    BUILDER_FOOD("Alimento constructor");

    private final String description;

    NutritionalFunctionType(String description) {
        this.description = description;
    }

}

