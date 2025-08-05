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

@Service
@AllArgsConstructor
public class MeasurementUnitServiceImpl implements MeasurementUnitService { // Implementar la interfaz

    private final MeasurementUnitRepository measurementUnitRepository;
    private final MeasurementUnitEntityMapper measurementUnitEntityMapper;
    private final MeasurementUnitDtoMapper measurementUnitDtoMapper;


    @Override
    public MeasurementUnitEntity findOrCreateByDataDto(Long foodId, MeasurementUnitRequestDto measurementUnitRequestDto) {

        return measurementUnitRepository.findByUnitAndFood_Id(measurementUnitRequestDto.unit(), foodId)
                .orElseGet(() -> measurementUnitRepository
                        .save(measurementUnitEntityMapper.toEntity(
                                measurementUnitDtoMapper.toModel(measurementUnitRequestDto))));
    }

    @Override
    public MeasurementUnitResponseDto findById(Long id) {
        return measurementUnitDtoMapper.toDto(
                measurementUnitEntityMapper.toModel(findEntityById(id)));
    }

    @Override
    public MeasurementUnitEntity findEntityById(Long id) {
        return measurementUnitRepository.findById(id)
                .orElseThrow(() -> new MeasurementUnitNotFoundException(id));
    }

    @Override
    public MeasurementUnit findModelById(Long id) {
        return measurementUnitEntityMapper.toModel(findEntityById(id));
    }

    @Override
    public List<MeasurementUnitResponseDto> findByFood(Long foodId) {
        return measurementUnitRepository.findByFood_Id(foodId)
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
    public void deleteMeasurementUnit(Long measurementUnitId) {
        MeasurementUnitEntity measurementUnit = this.findEntityById(measurementUnitId);
        measurementUnitRepository.delete(measurementUnit);
    }
}
