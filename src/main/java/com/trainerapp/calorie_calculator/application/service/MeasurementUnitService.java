package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.MeasurementUnit;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MeasurementUnitResponseDto;

import java.util.List;

public interface MeasurementUnitService {
    MeasurementUnitEntity findOrCreateByDataDto(Long foodId, MeasurementUnitRequestDto measurementUnit);
    MeasurementUnitResponseDto findById(Long id);
    MeasurementUnitEntity findEntityById(Long id);
    MeasurementUnit findModelById(Long id);
    List<MeasurementUnitResponseDto> findByFood(Long foodId);
    List<MeasurementUnitEntity> getAll();
    void deleteMeasurementUnit(Long measurementUnitId);
}
