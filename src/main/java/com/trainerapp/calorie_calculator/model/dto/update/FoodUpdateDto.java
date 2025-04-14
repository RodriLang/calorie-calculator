package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;

import java.util.List;

public record FoodDataDto(
        String name,
        FoodOriginType foodOrigin,
        List<NutritionalFunctionType> nutritionalFunctions,
        NutritionalInfoDataDto nutritionalInfo,
        List<MicronutrientContentDataDto> micronutrients,
        List<Long> tagIds) {
}