package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Recipe;
import com.trainerapp.calorie_calculator.web.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;

import java.util.List;
import java.util.UUID;

public interface RecipeService {

    List<RecipeResponseDto> getAllRecipes();

    Recipe getModelById(UUID id);

    RecipeResponseDto getRecipeById(UUID id);

    RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto);

    RecipeResponseDto updateRecipe(UUID recipeId, RecipeRequestDto recipeRequestDto);

    void deleteRecipe(UUID recipeId);

    RecipeResponseDto addSectionToRecipe(UUID recipeId, SectionRequestDto sectionRequestDto);

    RecipeResponseDto removeSectionFromRecipe(UUID recipeId, UUID sectionId);

    RecipeResponseDto addTags(UUID recipeId, List<TagRequestDto> tagsData);

    RecipeResponseDto removeTags(UUID recipeId, List<UUID> tagIds);
}