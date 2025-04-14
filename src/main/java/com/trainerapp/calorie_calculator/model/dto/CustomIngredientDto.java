package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.UnitType;

public record CustomIngredientDto(

        String food,
        Double quantity,
        UnitType unit,
        String note) {
}
