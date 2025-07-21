package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.MeasurementUnitResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MeasurementUnitMapper {

    MeasurementUnitResponseDto toDto(MeasurementUnit measurementUnit);

    MeasurementUnit toEntity(MeasurementUnitRequestDto measurementUnitRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMeasurementUnitFromDto(MeasurementUnitRequestDto dto, @MappingTarget MeasurementUnit measurementUnit);
}
