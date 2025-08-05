package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.MeasurementUnit;
import com.trainerapp.calorie_calculator.web.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.MeasurementUnitResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MeasurementUnitDtoMapper {

    MeasurementUnitResponseDto toDto(MeasurementUnit model);

    MeasurementUnit toModel(MeasurementUnitRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MeasurementUnit updateMeasurementUnitFromDto(MeasurementUnitRequestDto dto, @MappingTarget MeasurementUnit.MeasurementUnitBuilder builder);
}
