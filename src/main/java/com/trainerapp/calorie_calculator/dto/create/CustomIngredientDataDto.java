package com.trainerapp.calorie_calculator.dto.create;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomIngredientDataDto(

        @NotBlank
        String food,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true, message = "La cantidad no puede ser menor a cero.")
        Double quantity,

        @NotNull
        UnitType unit,

        String note) {
}
