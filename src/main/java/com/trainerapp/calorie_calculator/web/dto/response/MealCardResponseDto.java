package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.List;
import java.util.UUID;

public record MealCardResponseDto(

        UUID id,

        String name,

        String url,

        String difficulty,

        String preparationTime,

        List<TagResponseDto> tags
) {
}
