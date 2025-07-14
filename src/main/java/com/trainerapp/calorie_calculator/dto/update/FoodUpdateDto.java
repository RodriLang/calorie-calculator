package com.trainerapp.calorie_calculator.model.dto.update;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientContentDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.NutritionalInfoDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;

import java.util.List;
import java.util.Optional;

public record FoodUpdateDto(
        Optional<String> name,
        Optional<FoodOriginType> foodOrigin,
        Optional<List<NutritionalFunctionType>> nutritionalFunctions,
        Optional<NutritionalInfoDataDto> nutritionalInfo,
        Optional<List<MicronutrientContentDataDto>> micronutrients,
        Optional<List<TagDataDto>> tags) {
}