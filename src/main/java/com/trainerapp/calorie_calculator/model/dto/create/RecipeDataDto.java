package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.util.List;
public record RecipeDataDto(

        @NotBlank(message = "El nombre no puede estar vacío.")
        String name,

        @NotNull(message = "La lista de ingredientes no puede ser nula.")
        @Size (min = 1, message = "Debe contener al menos un ingrediente.")
        List<IngredientDataDto> ingredients,

        List<CustomIngredientDataDto> customIngredients,

        @NotBlank(message = "La descripción no puede estar vacía.")
        String shortDescription,

        @NotNull(message = "La lista de pasos no puede ser nula.")
        List<@NotBlank(message = "Los pasos no pueden estar vacíos.") String> steps,

        @NotNull(message = "El tiempo de preparación no puede ser nulo.")
        Duration preparationTime,

        @NotNull(message = "La dificultad no puede ser nula.")
        DifficultyType difficulty
) {}
