package com.trainerapp.calorie_calculator.dto;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import lombok.Builder;

import java.util.List;

@Builder
public record FoodDto(
        Long id,
        String name,
        FoodOriginType foodOrigin,
        List<NutritionalFunctionType> nutritionalFunctions,
        NutritionalInfoDto nutritionalInfo,
        List<MicronutrientContentDto> micronutrients,
        List<TagDto> tags) {
}
