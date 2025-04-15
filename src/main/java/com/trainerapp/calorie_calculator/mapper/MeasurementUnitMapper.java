package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MeasurementUnitDto;
import com.trainerapp.calorie_calculator.model.dto.create.MeasurementUnitDataDto;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;

public interface MeasurementUnitMapper {

    MeasurementUnit fromDto(MeasurementUnitDto measurementUnitDto);

    MeasurementUnitDto toDto(MeasurementUnit measurementUnit);

    MeasurementUnit fromDataDto(MeasurementUnitDataDto measurementUnitDataDto);
}
