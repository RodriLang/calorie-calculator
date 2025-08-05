package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {IngredientEntityMapper.class,
                SeasoningEntityMapper.class,
                StepEntityMapper.class})
public interface SectionEntityMapper {

    SectionEntity toEntity(Section model);

    Section toModel(SectionEntity entity);
}
