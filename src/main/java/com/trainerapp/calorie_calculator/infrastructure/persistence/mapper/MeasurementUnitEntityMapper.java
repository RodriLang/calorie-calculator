package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.MeasurementUnit;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MeasurementUnitEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementUnitEntityMapper {

    MeasurementUnitEntity toEntity(MeasurementUnit model);

    MeasurementUnit toModel(MeasurementUnitEntity entity);
}
