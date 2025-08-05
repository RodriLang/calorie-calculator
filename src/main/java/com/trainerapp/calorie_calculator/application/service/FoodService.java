package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Food;
import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.domain.enums.FoodOriginType;

import java.util.List;

public interface FoodService {
    List<FoodResponseDto> getAll();
    FoodResponseDto getById(long id);
    FoodResponseDto create(FoodRequestDto food);
    void deleteById(long id);
    FoodResponseDto update(Long id, FoodRequestDto updatedFood);
    List<FoodResponseDto> findByCaloriesBetween(Integer calories1, Integer calories2);
    List<FoodResponseDto> findByFoodOrigin(FoodOriginType foodOriginType);
    FoodResponseDto addMicronutrient(Long id, MicronutrientContentRequestDto micronutrientContent);
    FoodResponseDto addOrUpdateMicronutrients(Long id, List<MicronutrientContentRequestDto> micronutrientContents);
    void removeMicronutrient(Long foodId, Long micronutrientId);
    void removeMicronutrients(Long foodId, List<Long> micronutrientIds);
    FoodResponseDto addMeasurementUnit(MeasurementUnitRequestDto unitDto);
    FoodResponseDto addTags(Long foodId, List<TagRequestDto> tagsData);
    FoodResponseDto removeTags(Long foodId, List<Long> tagIds);
    FoodResponseDto removeMeasurementUnit(Long unitId);
    Food findModelById(Long id);
}
