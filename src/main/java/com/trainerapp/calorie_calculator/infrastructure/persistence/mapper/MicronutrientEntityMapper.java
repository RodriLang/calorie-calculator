package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Micronutrient;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.MicronutrientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MicronutrientEntityMapper {

    MicronutrientEntity toEntity(Micronutrient model);

    Micronutrient toModel(MicronutrientEntity entity);
}
