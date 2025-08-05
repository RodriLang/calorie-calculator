package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SeasoningEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomIngredientEntityMapper {

    Seasoning toModel(SeasoningEntity model);
    SeasoningEntity toEntity(Seasoning entity);
}
