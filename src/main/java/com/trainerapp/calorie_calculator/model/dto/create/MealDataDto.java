package com.trainerapp.calorie_calculator.model.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record MealDataDto(

        @NotBlank(message = "El nombre no puede estar vacío.")
        String name,

        @NotBlank(message = "La descripción corta no puede estar vacía.")
        String shortDescription,

        @NotNull(message = "La URL no puede ser nula.")
        @NotBlank(message = "La URL no puede estar vacía.")
        String url,

        @NotNull(message = "La lista de recetas no puede ser nula.")
        @Size(min = 1, message = "Debe contener al menos una receta.")
        List<@NotNull @Positive(message = "Los IDs de receta deben ser positivos.") Long> recipeIds,

        List<TagDataDto> tags
) {
}

