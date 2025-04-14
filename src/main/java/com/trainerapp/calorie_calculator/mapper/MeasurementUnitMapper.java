package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MeasurementUnitDto;
import com.trainerapp.calorie_calculator.model.dto.create.MeasurementUnitDataDto;
import com.trainerapp.calorie_calculator.model.entity.MeasurementUnit;
import com.trainerapp.calorie_calculator.service.FoodService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        FoodService.class,
        FoodMapper.class,
})
public interface MeasurementUnitMapper {

    MeasurementUnit fromDto(MeasurementUnitDto measurementUnitDto);

    MeasurementUnitDto toDto(MeasurementUnit measurementUnit);

    MeasurementUnit fromDataDto(MeasurementUnitDataDto measurementUnitDataDto);
}
