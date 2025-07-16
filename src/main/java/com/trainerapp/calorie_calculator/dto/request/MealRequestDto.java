package com.trainerapp.calorie_calculator.dto.request;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.validations.OnCreate;
import com.trainerapp.calorie_calculator.validations.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;

import java.util.List;

public record MealRequestDto(

        @NotBlank(message = "El nombre no puede estar vacío.", groups = OnCreate.class)
        String name,

        @NotBlank(message = "La descripción corta no puede estar vacía.", groups = OnCreate.class)
        String shortDescription,

        @NotNull(message = "La URL no puede ser nula.", groups = OnCreate.class)
        @NotBlank(message = "La URL no puede estar vacía.", groups = {OnCreate.class, OnUpdate.class})
        String url,

        @NotNull(message = "La lista de recetas no puede ser nula.", groups = OnCreate.class)
        @Size(min = 1, message = "Debe contener al menos una receta.", groups = {OnCreate.class, OnUpdate.class})
        List<RecipeResponseDto> recipes,

        List<TagRequestDto> tags
) {
}

