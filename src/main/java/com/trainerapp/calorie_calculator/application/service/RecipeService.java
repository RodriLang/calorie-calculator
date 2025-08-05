package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Recipe;
import com.trainerapp.calorie_calculator.web.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;

import java.util.List;

public interface RecipeService {
    List<RecipeResponseDto> getAllRecipes();

    Recipe getModelById(Long id);

    RecipeResponseDto getRecipeById(Long id);

    RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto);

    RecipeResponseDto updateRecipe(Long recipeId, RecipeRequestDto recipeRequestDto);

    void deleteRecipe(Long recipeId);

    RecipeResponseDto addSectionToRecipe(Long recipeId, SectionRequestDto sectionRequestDto);

    RecipeResponseDto removeSectionFromRecipe(Long recipeId, Long sectionId);

    RecipeResponseDto addTags(Long recipeId, List<TagRequestDto> tagsData);

    RecipeResponseDto removeTags(Long recipeId, List<Long> tagIds);
}