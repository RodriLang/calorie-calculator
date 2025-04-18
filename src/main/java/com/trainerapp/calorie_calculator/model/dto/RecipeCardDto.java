package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.FlavorType;
import lombok.Builder;

import java.util.List;

@Builder
public record RecipeCardDto(
        Long id,
        String name,
        String url,
        String difficulty,
        String preparationTime,
        List<TagDto> tags,
        String energy,
        FlavorType flavorType
        ) {
}
