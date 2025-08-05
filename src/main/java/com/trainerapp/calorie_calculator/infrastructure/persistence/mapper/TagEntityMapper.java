package com.trainerapp.calorie_calculator.infrastructure.persistence.mapper;

import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagEntityMapper {

    TagEntity toEntity(Tag model);

    Tag toModel(TagEntity entity);

}
