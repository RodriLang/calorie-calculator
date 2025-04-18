package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.SeasoningNotFoundException;
import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.RecipeSectionNotFoundException;
import com.trainerapp.calorie_calculator.exception.StepNotFoundException;
import com.trainerapp.calorie_calculator.mapper.RecipeSectionMapper;
import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.SeasoningDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeSectionDataDto;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.repository.RecipeSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class RecipeSectionService {


    private final RecipeSectionRepository recipeSectionRepository;
    private final RecipeSectionMapper recipeSectionMapper;

    private final IngredientService ingredientService;
    private final SeasoningService seasoningService;

    public RecipeSectionDto saveRecipe(RecipeSectionDataDto recipeSectionDataDto) {
        return recipeSectionMapper.toDto(recipeSectionRepository.save(recipeSectionMapper.fromDataDto(recipeSectionDataDto)));
    }

    public List<RecipeSectionDto> getRecipes() {
        return recipeSectionRepository.findAll()
                .stream()
                .map(recipeSectionMapper::toDto)
                .toList();
    }

    public RecipeSectionDto findById(Long recipeId) {
        return recipeSectionMapper.toDto(recipeSectionRepository.findById(recipeId).orElseThrow(()
                -> new RecipeSectionNotFoundException(recipeId)));
    }

    public RecipeSection findEntityById(Long recipeId) {
        return recipeSectionRepository.findById(recipeId).orElseThrow(()
                -> new RecipeSectionNotFoundException(recipeId));
    }


    public RecipeSectionDto createRecipe(RecipeSectionDataDto recipeSectionDataDto) {

        RecipeSection recipeSection = new RecipeSection();
        recipeSection.setName(recipeSectionDataDto.name());
        recipeSection.setDescription(recipeSectionDataDto.shortDescription());


        // Mapear ingredientes
        for (IngredientDataDto ingredientDto : recipeSectionDataDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipeSection.getIngredients().add(ingredient);
        }

        // Mapear custom ingredients
        for (SeasoningDataDto customDto : recipeSectionDataDto.customIngredients()) {
            Seasoning seasoning = seasoningService.create(customDto);
            recipeSection.getSeasonings().add(seasoning);
        }

        // Guardar los pasos
        recipeSection.setSteps(new ArrayList<>(recipeSectionDataDto.steps()));

        recipeSection = recipeSectionRepository.save(recipeSection);

        return recipeSectionMapper.toDto(recipeSection);
    }


    public RecipeSectionDto updateRecipe(Long recipeId, RecipeSectionDataDto recipeSectionDataDto) {

        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        recipeSection.setName(recipeSectionDataDto.name());
        recipeSection.setDescription(recipeSectionDataDto.shortDescription());


        // Limpiar y actualizar ingredientes
        recipeSection.getIngredients().clear();
        for (IngredientDataDto ingredientDto : recipeSectionDataDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipeSection.getIngredients().add(ingredient);
        }

        // Limpiar y actualizar custom ingredients
        recipeSection.getSeasonings().clear();
        for (SeasoningDataDto customDto : recipeSectionDataDto.customIngredients()) {
            Seasoning seasoning = seasoningService.create(customDto);
            recipeSection.getSeasonings().add(seasoning);
        }

        // Actualizar pasos
        recipeSection.getSteps().clear();
        recipeSection.getSteps().addAll(recipeSectionDataDto.steps());

        recipeSection = recipeSectionRepository.save(recipeSection);

        return recipeSectionMapper.toDto(recipeSection);
    }


    public void deleteRecipe(Long recipeId) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));
        recipeSectionRepository.delete(recipeSection);
    }


    public RecipeSectionDto addIngredientToRecipe(Long recipeId, IngredientDataDto ingredientDataDto) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        Ingredient ingredient = ingredientService.create(ingredientDataDto);

        recipeSection.getIngredients().add(ingredient);
        recipeSection = recipeSectionRepository.save(recipeSection);

        return recipeSectionMapper.toDto(recipeSection);
    }

    public RecipeSectionDto removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        boolean removed = recipeSection.getIngredients().removeIf(i -> i.getId().equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipeSection id: " + recipeId);
        }

        recipeSection = recipeSectionRepository.save(recipeSection);
        return recipeSectionMapper.toDto(recipeSection);
    }


    public RecipeSectionDto updateIngredientInRecipe(Long recipeId, Long ingredientId, IngredientDataDto newIngredientData) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        Ingredient ingredient = recipeSection.getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        // Actualizar usando IngredientService
        ingredientService.update(ingredient, newIngredientData);

        recipeSection = recipeSectionRepository.save(recipeSection);
        return recipeSectionMapper.toDto(recipeSection);
    }


//Seasonings

    public RecipeSectionDto addSeasoning(Long recipeId, SeasoningDataDto seasoningDataDto) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        recipeSection.getSeasonings().add(seasoningService.create(seasoningDataDto));
        recipeSection = recipeSectionRepository.save(recipeSection);

        return recipeSectionMapper.toDto(recipeSection);
    }

    public RecipeSectionDto updateSeasoning(Long recipeId, Long customIngredientId, SeasoningDataDto updatedData) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        Seasoning ingredient = recipeSection.getSeasonings().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new SeasoningNotFoundException(customIngredientId));

        // Update fields

        seasoningService.update(ingredient, updatedData);

        recipeSection = recipeSectionRepository.save(recipeSection);
        return recipeSectionMapper.toDto(recipeSection);
    }


    public RecipeSectionDto removeSeasoning(Long recipeId, Long customIngredientId) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        Seasoning ingredient = recipeSection.getSeasonings().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new SeasoningNotFoundException(customIngredientId));

        recipeSection.getSeasonings().remove(ingredient);

        recipeSection = recipeSectionRepository.save(recipeSection);
        return recipeSectionMapper.toDto(recipeSection);
    }


//Steps

    public RecipeSectionDto addStepToRecipe(Long recipeId, String stepDescription) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        recipeSection.getSteps().add(stepDescription); // Añadir el paso

        recipeSection = recipeSectionRepository.save(recipeSection);

        return recipeSectionMapper.toDto(recipeSection);
    }

    public RecipeSectionDto updateStepInRecipe(Long recipeId, int stepIndex, String newStepDescription) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        if (stepIndex >= 0 && stepIndex < recipeSection.getSteps().size()) {
            recipeSection.getSteps().set(stepIndex, newStepDescription); // Reemplazar paso
            recipeSection = recipeSectionRepository.save(recipeSection);
            return recipeSectionMapper.toDto(recipeSection);
        } else {
            throw new StepNotFoundException("Step index out of range");
        }
    }

    public RecipeSectionDto removeStepFromRecipe(Long recipeId, String stepDescription) {
        RecipeSection recipeSection = recipeSectionRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeSectionNotFoundException(recipeId));

        if (recipeSection.getSteps().remove(stepDescription)) {
            recipeSection = recipeSectionRepository.save(recipeSection);
            return recipeSectionMapper.toDto(recipeSection);
        } else {
            throw new StepNotFoundException("Step not found in recipeSection");
        }
    }

}
