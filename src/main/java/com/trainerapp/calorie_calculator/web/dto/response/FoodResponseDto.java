package com.trainerapp.calorie_calculator.web.dto.response;

import java.util.List;
import java.util.UUID;

public record FoodResponseDto(

        UUID id,

        String name,

        String foodOrigin,

        List<String> nutritionalFunctions,

        NutritionalInfoResponseDto nutritionalInfo,

        List<MicronutrientContentResponseDto> micronutrients,

        List<TagResponseDto> tags
) {
}
