package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Ingredient;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.IngredientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FoodEntityMapper.class)
public interface IngredientEntityMapper {

    IngredientEntity toEntity(Ingredient model);

    Ingredient toModel(IngredientEntity entity);
}

