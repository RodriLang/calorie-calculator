package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.repository.MeasurementUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementUnitService {

    private final MeasurementUnitRepository measurementUnitRepository;

    public MeasurementUnitService(MeasurementUnitRepository measurementUnitRepository) {
        this.measurementUnitRepository = measurementUnitRepository;
    }

    public List<MeasurementUnit> getAll(){
        return measurementUnitRepository.findAll();
    }
}
