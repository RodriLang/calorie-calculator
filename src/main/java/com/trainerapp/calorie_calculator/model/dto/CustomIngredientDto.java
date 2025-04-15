package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.UnitType;
import lombok.Builder;

@Builder
public record CustomIngredientDto(

        String food,
        Double quantity,
        String unit,
        String note) {
}
