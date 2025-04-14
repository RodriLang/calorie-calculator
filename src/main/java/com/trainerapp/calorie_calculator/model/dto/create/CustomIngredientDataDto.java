package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.UnitType;

public record CustomIngredientDataDto(
        String food,
        Double quantity,
        UnitType unit,
        String note) {
}
