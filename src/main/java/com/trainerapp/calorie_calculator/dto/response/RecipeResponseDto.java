package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;

import java.util.List;

public record RecipeResponseDto(

        Long id,
        String name,
        String preparationTime,
        Integer servings,
        String description,
        String url,
        List<SectionRequestDto> sections,
        List<TagRequestDto> tags
) {
}