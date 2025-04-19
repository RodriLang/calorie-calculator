package com.trainerapp.calorie_calculator.model.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TagDataDto(

        @NotBlank(message = "La etiqueta no puede estar vacía.")
        String label
        

) {
}
