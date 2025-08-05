package com.trainerapp.calorie_calculator.web.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record MicronutrientContentRequestDto(

        @NotNull(message = "El ID no puede ser nulo.")
        @Min(value = 1, message = "El ID debe ser mayor o igual a 1.")
        UUID micronutrientId,

        @NotNull
        @DecimalMin(value = "0.000001", inclusive = true, message = "El valor debe ser mayor que cero.")
        Double amountPerUnit
) {
}