package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.enums.TagType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TagRequestDto(

        @NotBlank(message = "La etiqueta no puede estar vacía.")
        String label,

        @NotNull(message = "El tipo de etiqueta no puede ser nulo.")
        TagType tagType

) {
}
