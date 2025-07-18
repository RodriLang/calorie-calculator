package com.trainerapp.calorie_calculator.dto.response;

import java.util.List;

public record SectionResponseDto(

        String name,

        List<IngredientResponseDto> ingredients,

        List<SeasoningResponseDto> seasonings,

        String description,

        List<StepResponseDto> steps,

        String preparationTime,

        String difficulty
) {}