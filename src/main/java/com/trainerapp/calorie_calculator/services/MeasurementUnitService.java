package com.trainerapp.calorie_calculator.services;

import com.trainerapp.calorie_calculator.models.MeasurementUnit;
import com.trainerapp.calorie_calculator.repositories.MeasurementUnitRepository;
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
