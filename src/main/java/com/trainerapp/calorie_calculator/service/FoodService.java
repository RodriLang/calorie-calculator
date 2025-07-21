package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.model.entity.Food;

import java.util.List;

public interface FoodService {
    List<FoodResponseDto> getAll();
    FoodResponseDto getById(long id);
    FoodResponseDto save(FoodRequestDto food);
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
    Food findEntityById(Long id);
}
