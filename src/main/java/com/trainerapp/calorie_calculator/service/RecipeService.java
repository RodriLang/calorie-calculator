package com.trainerapp.calorie_calculator.service;
import com.trainerapp.calorie_calculator.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import java.util.List;
public interface RecipeService {
    List<RecipeResponseDto> getAllRecipes();
    Recipe getRecipeEntityById(Long id);
    RecipeResponseDto getRecipeById(Long id);
    RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto);
    RecipeResponseDto updateRecipe(Long recipeId, RecipeRequestDto recipeRequestDto);
    void deleteRecipe(Long recipeId);
    RecipeResponseDto addSectionToRecipe(Long recipeId, SectionRequestDto sectionRequestDto);
    RecipeResponseDto removeSectionFromRecipe(Long recipeId, Long sectionId);
    RecipeResponseDto addTags(Long recipeId, List<TagRequestDto> tagsData);
    RecipeResponseDto removeTags(Long recipeId, List<Long> tagIds);
}