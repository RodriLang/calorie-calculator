package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;

import java.util.List;

public record CreateFoodDto(
        String name,
        FoodOriginType foodOrigin,
        List<NutritionalFunctionType> nutritionalFunctions,
        CreateNutritionalInfoDto nutritionalInfo,
        List<CreateMicronutrientContentDto> micronutrients,
        List<Long> tagIds) {
}