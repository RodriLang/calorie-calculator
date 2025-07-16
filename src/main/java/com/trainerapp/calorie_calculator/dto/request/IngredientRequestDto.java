package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IngredientRequestDto(

        @NotNull(message = "El ID no puede ser nulo.")
        //@Min(value = 1, message = "El ID debe ser mayor o igual a 1.")
        FoodResponseDto food,


        @NotNull
        @Positive(message = "La cantidad debe ser mayor que cero.")
        Double quantity,


        @NotNull(message = "El ID no puede ser nulo.")
        @Min(value = 1, message = "El ID debe ser mayor o igual a 1.")
        Long measurementUnitId) {
}

