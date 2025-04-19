package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeSectionDataDto;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;


public interface RecipeSectionMapper {


    RecipeSection fromDto(RecipeSectionDto recipeSectionDto);

    RecipeSectionDto toDto(RecipeSection recipeSection);

    RecipeSection fromDataDto(RecipeSectionDataDto recipeSectionDataDto);
}
