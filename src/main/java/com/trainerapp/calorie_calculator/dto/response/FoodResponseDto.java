package com.trainerapp.calorie_calculator.dto.response;

import java.util.List;

public record FoodResponseDto(
        Long id,
        String name,
        String foodOrigin,
        List<String> nutritionalFunctions,
        NutritionalInfoResponseDto nutritionalInfo,
        List<MicronutrientContentResponseDto> micronutrients,
        List<TagResponseDto> tags) {
}
