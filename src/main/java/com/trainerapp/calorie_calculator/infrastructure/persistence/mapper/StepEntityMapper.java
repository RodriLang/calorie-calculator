package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Step;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.StepEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")

public interface StepEntityMapper {

    StepEntity toEntity(Step model);

    Step toModel(StepEntity entity);
}
