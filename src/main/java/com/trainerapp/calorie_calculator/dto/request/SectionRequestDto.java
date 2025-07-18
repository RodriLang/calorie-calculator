package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.enums.DifficultyType;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.model.entity.Step;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Duration;
import java.util.List;

public record SectionRequestDto(

        @NotBlank(message = "El nombre no puede estar vacío.")
        String name,

        @NotNull(message = "La lista de ingredientes no puede ser nula.")
        @Size (min = 1, message = "Debe contener al menos un ingrediente.")
        List<IngredientRequestDto> ingredients,

        List<SeasoningRequestDto> seasonings,

        @NotBlank(message = "La descripción no puede estar vacía.")
        String description,

        @NotNull(message = "La lista de pasos no puede ser nula.")
        List<@NotBlank(message = "Los pasos no pueden estar vacíos.") StepRequestDto> steps,

        @NotNull(message = "El tiempo de preparación no puede ser nulo.")
        Duration preparationTime,

        @NotNull(message = "La dificultad no puede ser nula.")
        DifficultyType difficulty
) {}