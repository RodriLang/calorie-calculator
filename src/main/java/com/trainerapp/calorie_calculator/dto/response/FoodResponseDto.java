package com.trainerapp.calorie_calculator.dto.response;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import lombok.Builder;

import java.util.List;

@Builder
public record FoodResponseDto(
        Long id,
        String name,
        FoodOriginType foodOrigin,
        List<NutritionalFunctionType> nutritionalFunctions,
        NutritionalInfoResponseDto nutritionalInfo,
        List<MicronutrientContentResponseDto> micronutrients,
        List<TagResponseDto> tags) {
}
