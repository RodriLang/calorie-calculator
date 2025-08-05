package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Ingredient;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.IngredientResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = FoodDtoMapper.class)
public interface IngredientDtoMapper {

    IngredientResponseDto toDto(Ingredient model);

    Ingredient toModel(IngredientRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ingredient updateFromDto(IngredientRequestDto dto, @MappingTarget Ingredient.IngredientBuilder builder);
}

