package com.trainerapp.calorie_calculator.dto.create;

import com.trainerapp.calorie_calculator.enums.UnitType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MeasurementUnitDataDto(

        @NotNull(message = "El ID no puede ser nulo.")
        @Min(value = 1, message = "El ID debe ser mayor o igual a 1.")
        Long foodId,

        @NotNull(message = "El tipo de unidad no puede ser nulo.")
        UnitType unit,

        @NotNull(message = "El peso por unidad no puede ser nulo.")
        @DecimalMin(value = "0.000001", inclusive = true, message = "El peso por unidad debe ser mayor que cero.")
        Double gramsPerUnit) {
}

