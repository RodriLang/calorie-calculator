package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SectionEntity;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SectionResponseDto;

import java.util.List;
import java.util.UUID;

public interface SectionService {


    List<SectionResponseDto> getSections();

    SectionResponseDto findById(UUID sectionId);

    SectionEntity findEntityById(UUID sectionId);

    Section findModelById(UUID sectionId);

    SectionResponseDto createSection(SectionRequestDto sectionRequestDto);

    SectionResponseDto updateSection(UUID sectionId, SectionRequestDto sectionRequestDto);

    void deleteSection(UUID sectionId);

    SectionResponseDto addIngredientToSection(UUID sectionId, IngredientRequestDto ingredientRequestDto);

    SectionResponseDto removeIngredientFromSection(UUID sectionId, UUID ingredientId);


    SectionResponseDto updateIngredientInSection(UUID recipeId, UUID ingredientId, IngredientRequestDto newIngredientData);


//Seasonings

    SectionResponseDto addSeasoningToSection(UUID recipeId, UUID sectionId, SeasoningRequestDto seasoningRequestDto);

    SectionResponseDto updateSeasoning(UUID recipeId, UUID customIngredientId, SeasoningRequestDto updatedData);

    SectionResponseDto removeCustomIngredient(UUID recipeId, UUID customIngredientId);


//Steps

    SectionResponseDto addStepToRecipe(UUID sectionId, StepRequestDto stepRequestDto);

    SectionResponseDto updateStepInRecipe(UUID sectionId, Integer stepNumber, StepRequestDto stepRequestDto);

    SectionResponseDto removeStepFromSection(UUID recipeId, Integer stepNumber);

}
