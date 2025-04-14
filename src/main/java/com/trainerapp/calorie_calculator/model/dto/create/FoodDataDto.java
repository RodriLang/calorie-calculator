package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FoodDataDto(

        @NotBlank
        String name,
        @NotNull
        FoodOriginType foodOrigin,
        @NotNull
        List<NutritionalFunctionType> nutritionalFunctions,
        @NotNull
        NutritionalInfoDataDto nutritionalInfo,
        List<MicronutrientContentDataDto> micronutrients,
        List<TagDataDto> tags) {
}