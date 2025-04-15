package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.MicronutrientType;
import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MicronutrientDataDto(

        @NotBlank(message = "El nombre no puede estar vacío.")
        String name,

        @NotNull(message = "La cantidad diaria no puede ser nula.")
        @DecimalMin(value = "0.0", inclusive = false, message = "La cantidad diaria debe ser mayor a cero.")
        Double dailyAmount,

        @NotNull(message = "La unidad no puede ser nula.")
        UnitType unit,

        @NotNull(message = "El tipo de micronutriente no puede ser nulo.")
        MicronutrientType type
) {}

