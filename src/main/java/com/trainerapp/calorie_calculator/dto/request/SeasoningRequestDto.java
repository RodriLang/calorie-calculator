package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeasoningRequestDto(

        @NotBlank
        String name,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true, message = "La cantidad no puede ser menor a cero.")
        Double amount,

        @NotNull
        UnitType unit,

        String label) {
}
