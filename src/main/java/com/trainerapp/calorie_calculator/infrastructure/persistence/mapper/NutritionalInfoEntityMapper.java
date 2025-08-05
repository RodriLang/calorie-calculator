package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;


import com.trainerapp.calorie_calculator.domain.model.NutritionalInfo;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.NutritionalInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MicronutrientEntityMapper.class)
public interface NutritionalInfoEntityMapper {

    NutritionalInfoEntity toEntity(NutritionalInfo model);

    NutritionalInfo toModel(NutritionalInfoEntity entity);
}

