package com.trainerapp.calorie_calculator.dto.update;

import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.enums.NutritionalFunctionType;
import com.trainerapp.calorie_calculator.dto.request.NutritionalInfoRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;

import java.util.List;
import java.util.Optional;

public record FoodUpdateDto(
        Optional<String> name,
        Optional<FoodOriginType> foodOrigin,
        Optional<List<NutritionalFunctionType>> nutritionalFunctions,
        Optional<NutritionalInfoRequestDto> nutritionalInfo,
        Optional<List<MicronutrientContentRequestDto>> micronutrients,
        Optional<List<TagRequestDto>> tags) {
}