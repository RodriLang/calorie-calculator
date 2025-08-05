package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.List;
import java.util.UUID;

public record SectionResponseDto(

        UUID id,

        String name,

        List<IngredientResponseDto> ingredients,

        List<SeasoningResponseDto> seasonings,

        String description,

        List<StepResponseDto> steps,

        String preparationTime,

        String difficulty
) {
}