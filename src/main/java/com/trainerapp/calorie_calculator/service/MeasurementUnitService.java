package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.dto.response.MeasurementUnitResponseDto;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;

import java.util.List;

public interface MeasurementUnitService {
    MeasurementUnit findOrCreateByDataDto(Long foodId, MeasurementUnitRequestDto measurementUnit);
    MeasurementUnitResponseDto findById(Long id);
    MeasurementUnit findEntityById(Long id);
    List<MeasurementUnitResponseDto> findByFood(Long foodId);
    List<MeasurementUnit> getAll();
}
