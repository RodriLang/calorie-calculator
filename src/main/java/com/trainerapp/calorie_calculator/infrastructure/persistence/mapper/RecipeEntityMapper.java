package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Recipe;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.RecipeEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {
                SectionEntityMapper.class,
                TagEntityMapper.class
        })
public interface RecipeEntityMapper {

    RecipeEntity toEntity(Recipe model);

    Recipe toModel(RecipeEntity entity);
}