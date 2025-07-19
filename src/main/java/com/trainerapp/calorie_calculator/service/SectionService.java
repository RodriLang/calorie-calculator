package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.dto.response.SectionResponseDto;
import com.trainerapp.calorie_calculator.model.entity.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SectionService {


    List<SectionResponseDto> getSections();

    SectionResponseDto findById(Long sectionId);

    Section findEntityById(Long sectionId);

    SectionResponseDto createRecipe(SectionRequestDto sectionRequestDto);

    SectionResponseDto updateRecipe(Long sectionId, SectionRequestDto sectionRequestDto);

    void deleteSection(Long sectionId);

    SectionResponseDto addIngredientToRecipe(Long sectionId, IngredientRequestDto ingredientRequestDto);

    SectionResponseDto removeIngredientFromRecipe(Long sectionId, Long ingredientId);


    SectionResponseDto updateIngredientInRecipe(Long recipeId, Long ingredientId, IngredientRequestDto newIngredientData);


//Seasonings

    SectionResponseDto addSeasoningToSection(Long recipeId, Long sectionId, SeasoningRequestDto seasoningRequestDto);

    SectionResponseDto updateCustomIngredient(Long recipeId, Long customIngredientId, SeasoningRequestDto updatedData);

    SectionResponseDto removeCustomIngredient(Long recipeId, Long customIngredientId);


//Steps

    SectionResponseDto addStepToRecipe(Long sectionId, StepRequestDto stepRequestDto);

    SectionResponseDto updateStepInRecipe(Long sectionId, Integer stepNumber, StepRequestDto stepRequestDto);

    SectionResponseDto removeStepFromRecipe(Long recipeId, String stepDescription);

}
