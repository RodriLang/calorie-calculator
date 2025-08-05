package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Section;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SectionEntity;
import com.trainerapp.calorie_calculator.web.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SeasoningRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.SectionRequestDto;
import com.trainerapp.calorie_calculator.web.dto.request.StepRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.SectionResponseDto;

import java.util.List;

public interface SectionService {


    List<SectionResponseDto> getSections();

    SectionResponseDto findById(Long sectionId);

    SectionEntity findEntityById(Long sectionId);

    Section findModelById(Long sectionId);

    SectionResponseDto createSection(SectionRequestDto sectionRequestDto);

    SectionResponseDto updateSection(Long sectionId, SectionRequestDto sectionRequestDto);

    void deleteSection(Long sectionId);

    SectionResponseDto addIngredientToSection(Long sectionId, IngredientRequestDto ingredientRequestDto);

    SectionResponseDto removeIngredientFromSection(Long sectionId, Long ingredientId);


    SectionResponseDto updateIngredientInSection(Long recipeId, Long ingredientId, IngredientRequestDto newIngredientData);


//Seasonings

    SectionResponseDto addSeasoningToSection(Long recipeId, Long sectionId, SeasoningRequestDto seasoningRequestDto);

    SectionResponseDto updateSeasoning(Long recipeId, Long customIngredientId, SeasoningRequestDto updatedData);

    SectionResponseDto removeCustomIngredient(Long recipeId, Long customIngredientId);


//Steps

    SectionResponseDto addStepToRecipe(Long sectionId, StepRequestDto stepRequestDto);

    SectionResponseDto updateStepInRecipe(Long sectionId, Integer stepNumber, StepRequestDto stepRequestDto);

    SectionResponseDto removeStepFromSection(Long recipeId, String stepDescription);

}
