package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Food;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.FoodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                NutritionalInfoEntityMapper.class,
                TagEntityMapper.class
        })

public interface FoodEntityMapper {

    FoodEntity toEntity(Food model);

    Food toModel(FoodEntity entity);
}
