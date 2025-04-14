package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.MeasurementUnitNotFoundException;
import com.trainerapp.calorie_calculator.mapper.MeasurementUnitMapper;
import com.trainerapp.calorie_calculator.model.dto.MeasurementUnitDto;
import com.trainerapp.calorie_calculator.model.dto.create.MeasurementUnitDataDto;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.repository.MeasurementUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementUnitService {

    private final MeasurementUnitRepository measurementUnitRepository;
    private final MeasurementUnitMapper measurementUnitMapper;

    public MeasurementUnitService(MeasurementUnitRepository measurementUnitRepository, MeasurementUnitMapper measurementUnitMapper) {
        this.measurementUnitRepository = measurementUnitRepository;
        this.measurementUnitMapper = measurementUnitMapper;
    }


    public MeasurementUnit findOrCreateByDataDto(Long foodId, MeasurementUnitDataDto measurementUnit) {
        return measurementUnitRepository.findByUnitAndFood(foodId, measurementUnit.unit())
                .orElseGet(() -> measurementUnitRepository
                        .save(measurementUnitMapper.fromDataDto(measurementUnit)));
    }

    public MeasurementUnitDto findById(Long id) {
        return measurementUnitMapper.toDto(measurementUnitRepository.findById(id)
                .orElseThrow(()->new MeasurementUnitNotFoundException(id)));
    }


    public MeasurementUnit findEntityById(Long id) {
        return measurementUnitRepository.findById(id)
                .orElseThrow(()->new MeasurementUnitNotFoundException(id));
    }

    public List<MeasurementUnitDto> findByFood(Long foodId) {
        return measurementUnitRepository.findByFood(foodId)
                .stream()
                .map(measurementUnitMapper::toDto)
                .toList();
    }

    public List<MeasurementUnit> getAll() {
        return measurementUnitRepository.findAll();
    }
}
