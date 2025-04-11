package com.trainerapp.calorie_calculator.model.dto;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;

import java.util.List;

public record FoodDto(

        String name,

        FoodOriginType foodOrigin,

        List<NutritionalFunctionType> nutritionalFunctions,

        NutritionalInfoDto nutritionalInfo,

        List<MicronutrientContentDto> micronutrients) {
}
