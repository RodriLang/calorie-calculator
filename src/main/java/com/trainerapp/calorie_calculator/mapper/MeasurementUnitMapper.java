package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.MeasurementUnitResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementUnitMapper {

    MeasurementUnit fromDto(MeasurementUnitResponseDto measurementUnitResponseDto);

    MeasurementUnitResponseDto toDto(MeasurementUnit measurementUnit);

    MeasurementUnit fromDataDto(MeasurementUnitRequestDto measurementUnitRequestDto);
}
