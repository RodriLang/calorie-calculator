package com.trainerapp.calorie_calculator.web.dto.response;

import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;

import java.util.List;
import java.util.UUID;

public record RecipeResponseDto(

        UUID id,

        String name,

        String preparationTime,

        Integer servings,

        String description,

        String url,

        List<SectionRequestDto> sections,

        List<TagRequestDto> tags
) {
}