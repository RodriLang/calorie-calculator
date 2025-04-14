package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.CustomIngredientDto;
import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomIngredientMapper {

    CustomIngredient fromDto(CustomIngredientDto customIngredientDto);

    CustomIngredientDto toDto(CustomIngredient customIngredient);

    CustomIngredient fromDataDto(CustomIngredientDataDto customIngredientDataDto);
}
