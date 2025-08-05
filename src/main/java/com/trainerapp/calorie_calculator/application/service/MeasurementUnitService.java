package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.MeasurementUnit;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MeasurementUnitResponseDto;

import java.util.List;
import java.util.UUID;

public interface MeasurementUnitService {
    MeasurementUnitEntity findOrCreateByDataDto(UUID foodId, MeasurementUnitRequestDto measurementUnit);
    MeasurementUnitResponseDto findById(UUID id);
    MeasurementUnitEntity findEntityById(UUID id);
    MeasurementUnit findModelById(UUID id);
    List<MeasurementUnitResponseDto> findByFood(UUID foodId);
    List<MeasurementUnitEntity> getAll();
    void deleteMeasurementUnit(UUID measurementUnitId);
}
