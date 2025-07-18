package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {
                SectionMapper.class,
                TagMapper.class
        })
public interface RecipeMapper {
    
    RecipeResponseDto toDto(Recipe recipe);

    Recipe toEntity(RecipeRequestDto recipeRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRecipeFromDto(RecipeRequestDto dto, @MappingTarget Recipe recipe);
}