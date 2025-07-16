package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FoodRequestDto(

        @NotBlank(message = "El nombre no puede ser nulo.")
        String name,

        @NotNull(message = "El tipo de origen no puede ser nulo.")
        FoodOriginType foodOrigin,

        @NotNull(message = "La lista de funciones no puede ser nula.")
        @Size(min = 1, message = "Debe tener al menos una función nutricional")
        List<NutritionalFunctionType> nutritionalFunctions,

        @NotNull(message = "La información nutricional no puede ser nula.")
        NutritionalInfoRequestDto nutritionalInfo,

        List<MicronutrientContentRequestDto> micronutrients,

        List<TagRequestDto> tags) {
}