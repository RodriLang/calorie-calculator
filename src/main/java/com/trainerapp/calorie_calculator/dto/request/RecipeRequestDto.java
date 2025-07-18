package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.validations.OnCreate;
import com.trainerapp.calorie_calculator.validations.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.util.List;

public record RecipeRequestDto(

        @NotBlank(message = "El nombre no puede estar vacío.", groups = OnCreate.class)
        String name,

        @NotBlank(message = "La descripción no puede estar vacía.", groups = OnCreate.class)
        String description,

        @NotNull(message = "El tiempo de preparación no puede ser nulo.", groups = OnCreate.class)
        Duration preparationTime,

        @NotNull(message = "La cantidad de porciones no puede ser nula.")
        @Size(min = 1, message = "Debe contener al menos una porción.", groups = {OnCreate.class, OnUpdate.class})
        Integer portions,

        @NotNull(message = "La URL no puede ser nula.", groups = OnCreate.class)
        @NotBlank(message = "La URL no puede estar vacía.", groups = {OnCreate.class, OnUpdate.class})
        String url,

        @NotNull(message = "La lista de recetas no puede ser nula.", groups = OnCreate.class)
        @Size(min = 1, message = "Debe contener al menos una receta.", groups = {OnCreate.class, OnUpdate.class})
        List<SectionRequestDto> sections,

        List<TagRequestDto> tags
) {
}