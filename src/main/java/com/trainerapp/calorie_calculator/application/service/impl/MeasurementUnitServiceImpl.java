package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.application.service.MeasurementUnitService;
import com.trainerapp.calorie_calculator.domain.model.MeasurementUnit;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import com.trainerapp.calorie_calculator.web.dto.response.MeasurementUnitResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.application.exception.MeasurementUnitNotFoundException;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.MeasurementUnitEntityMapper;
import com.trainerapp.calorie_calculator.domain.repository.MeasurementUnitRepository;
import com.trainerapp.calorie_calculator.web.mapper.MeasurementUnitDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MeasurementUnitServiceImpl implements MeasurementUnitService { // Implementar la interfaz

    private final MeasurementUnitRepository measurementUnitRepository;
    private final MeasurementUnitEntityMapper measurementUnitEntityMapper;
    private final MeasurementUnitDtoMapper measurementUnitDtoMapper;


    @Override
    public MeasurementUnitEntity findOrCreateByDataDto(UUID foodId, MeasurementUnitRequestDto measurementUnitRequestDto) {

        return measurementUnitRepository.findByUnitAndFood_PublicId(measurementUnitRequestDto.unit(), foodId)
                .orElseGet(() -> measurementUnitRepository
                        .save(measurementUnitEntityMapper.toEntity(
                                measurementUnitDtoMapper.toModel(measurementUnitRequestDto))));
    }

    @Override
    public MeasurementUnitResponseDto findById(UUID id) {
        return measurementUnitDtoMapper.toDto(
                measurementUnitEntityMapper.toModel(findEntityById(id)));
    }

    @Override
    public MeasurementUnitEntity findEntityById(UUID id) {
        return measurementUnitRepository.findByPublicId(id)
                .orElseThrow(() -> new MeasurementUnitNotFoundException(id));
    }

    @Override
    public MeasurementUnit findModelById(UUID id) {
        return measurementUnitEntityMapper.toModel(findEntityById(id));
    }

    @Override
    public List<MeasurementUnitResponseDto> findByFood(UUID foodId) {
        return measurementUnitRepository.findByFood_PublicId(foodId)
                .stream()
                .map(measurementUnitEntityMapper::toModel)
                .map(measurementUnitDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<MeasurementUnitEntity> getAll() {
        return measurementUnitRepository.findAll();
    }

    @Override
    public void deleteMeasurementUnit(UUID measurementUnitId) {
        MeasurementUnitEntity measurementUnit = this.findEntityById(measurementUnitId);
        measurementUnitRepository.delete(measurementUnit);
    }
}
