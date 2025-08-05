package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Recipe;
import com.trainerapp.calorie_calculator.web.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                SectionDtoMapper.class,
                TagDtoMapper.class
        })
public interface RecipeDtoMapper {

    RecipeResponseDto toDto(Recipe model);

    Recipe toModel(RecipeRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recipe updateRecipeFromDto(RecipeRequestDto dto, @MappingTarget Recipe model);
}