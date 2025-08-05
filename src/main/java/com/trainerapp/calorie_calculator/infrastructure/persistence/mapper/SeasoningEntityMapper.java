package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Seasoning;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SeasoningEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasoningEntityMapper {

    SeasoningEntity toEntity(Seasoning model);

    Seasoning toModel(SeasoningEntity entity);

}
