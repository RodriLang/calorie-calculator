package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Food;
import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.domain.enums.FoodOriginType;

import java.util.List;
import java.util.UUID;

public interface FoodService {

    List<FoodResponseDto> getAll();

    FoodResponseDto getById(UUID id);

    FoodResponseDto create(FoodRequestDto food);

    void deleteById(UUID id);

    FoodResponseDto update(UUID id, FoodRequestDto updatedFood);

    List<FoodResponseDto> findByCaloriesBetween(Integer calories1, Integer calories2);

    List<FoodResponseDto> findByFoodOrigin(FoodOriginType foodOriginType);

    FoodResponseDto addMicronutrient(UUID id, MicronutrientContentRequestDto micronutrientContent);

    FoodResponseDto addOrUpdateMicronutrients(UUID id, List<MicronutrientContentRequestDto> micronutrientContents);

    void removeMicronutrient(UUID foodId, UUID micronutrientId);

    void removeMicronutrients(UUID foodId, List<UUID> micronutrientIds);

    FoodResponseDto addMeasurementUnit(MeasurementUnitRequestDto unitDto);

    FoodResponseDto addTags(UUID foodId, List<TagRequestDto> tagsData);

    FoodResponseDto removeTags(UUID foodId, List<UUID> tagIds);

    FoodResponseDto removeMeasurementUnit(UUID unitId);

    Food findModelById(UUID id);
}
