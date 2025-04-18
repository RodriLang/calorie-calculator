package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.exception.CustomIngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.RecipeNotFoundException;
import com.trainerapp.calorie_calculator.exception.StepNotFoundException;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.CustomIngredientDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.IngredientDataDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.model.entity.Seasoning;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.RecipeSection;
import com.trainerapp.calorie_calculator.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class RecipeService {


    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    private final IngredientService ingredientService;
    private final CustomIngredientService customIngredientService;

    public RecipeDto saveRecipe(RecipeDataDto recipeDataDto) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.fromDataDto(recipeDataDto)));
    }

    public List<RecipeDto> getRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public RecipeDto findById(Long recipeId) {
        return recipeMapper.toDto(recipeRepository.findById(recipeId).orElseThrow(()
                -> new RecipeNotFoundException(recipeId)));
    }

    public RecipeSection findEntityById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()
                -> new RecipeNotFoundException(recipeId));
    }


    public RecipeDto createRecipe(RecipeDataDto recipeDataDto) {

        RecipeSection recipeSection = new RecipeSection();
        recipeSection.setName(recipeDataDto.name());
        recipeSection.setShortDescription(recipeDataDto.shortDescription());
        recipeSection.setPreparationTime(recipeDataDto.preparationTime());
        recipeSection.setDifficulty(recipeDataDto.difficulty());

        // Mapear ingredientes
        for (IngredientDataDto ingredientDto : recipeDataDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipeSection.getIngredients().add(ingredient);
        }

        // Mapear custom ingredients
        for (CustomIngredientDataDto customDto : recipeDataDto.customIngredients()) {
            Seasoning seasoning = customIngredientService.create(customDto);
            recipeSection.getCustomIngredients().add(seasoning);
        }

        // Guardar los pasos
        recipeSection.setSteps(new ArrayList<>(recipeDataDto.steps()));

        recipeSection = recipeRepository.save(recipeSection);

        return recipeMapper.toDto(recipeSection);
    }


    public RecipeDto updateRecipe(Long recipeId, RecipeDataDto recipeDataDto) {

        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipeSection.setName(recipeDataDto.name());
        recipeSection.setShortDescription(recipeDataDto.shortDescription());
        recipeSection.setPreparationTime(recipeDataDto.preparationTime());
        recipeSection.setDifficulty(recipeDataDto.difficulty());

        // Limpiar y actualizar ingredientes
        recipeSection.getIngredients().clear();
        for (IngredientDataDto ingredientDto : recipeDataDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipeSection.getIngredients().add(ingredient);
        }

        // Limpiar y actualizar custom ingredients
        recipeSection.getCustomIngredients().clear();
        for (CustomIngredientDataDto customDto : recipeDataDto.customIngredients()) {
            Seasoning seasoning = customIngredientService.create(customDto);
            recipeSection.getCustomIngredients().add(seasoning);
        }

        // Actualizar pasos
        recipeSection.getSteps().clear();
        recipeSection.getSteps().addAll(recipeDataDto.steps());

        recipeSection = recipeRepository.save(recipeSection);

        return recipeMapper.toDto(recipeSection);
    }


    public void deleteRecipe(Long recipeId) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));
        recipeRepository.delete(recipeSection);
    }


    public RecipeDto addIngredientToRecipe(Long recipeId, IngredientDataDto ingredientDataDto) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Ingredient ingredient = ingredientService.create(ingredientDataDto);

        recipeSection.getIngredients().add(ingredient);
        recipeSection = recipeRepository.save(recipeSection);

        return recipeMapper.toDto(recipeSection);
    }

    public RecipeDto removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        boolean removed = recipeSection.getIngredients().removeIf(i -> i.getId().equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipe id: " + recipeId);
        }

        recipeSection = recipeRepository.save(recipeSection);
        return recipeMapper.toDto(recipeSection);
    }


    public RecipeDto updateIngredientInRecipe(Long recipeId, Long ingredientId, IngredientDataDto newIngredientData) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Ingredient ingredient = recipeSection.getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        // Actualizar usando IngredientService
        ingredientService.update(ingredient, newIngredientData);

        recipeSection = recipeRepository.save(recipeSection);
        return recipeMapper.toDto(recipeSection);
    }


//Custom Ingredients

    public RecipeDto addCustomIngredient(Long recipeId, CustomIngredientDataDto customIngredientDataDto) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipeSection.getCustomIngredients().add(customIngredientService.create(customIngredientDataDto));
        recipeSection = recipeRepository.save(recipeSection);

        return recipeMapper.toDto(recipeSection);
    }

    public RecipeDto updateCustomIngredient(Long recipeId, Long customIngredientId, CustomIngredientDataDto updatedData) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Seasoning ingredient = recipeSection.getCustomIngredients().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        // Actualizar campos
        customIngredientService.update(ingredient, updatedData);

        recipeSection = recipeRepository.save(recipeSection);
        return recipeMapper.toDto(recipeSection);
    }


    public RecipeDto removeCustomIngredient(Long recipeId, Long customIngredientId) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Seasoning ingredient = recipeSection.getCustomIngredients().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        recipeSection.getCustomIngredients().remove(ingredient);

        recipeSection = recipeRepository.save(recipeSection);
        return recipeMapper.toDto(recipeSection);
    }


//Steps

    public RecipeDto addStepToRecipe(Long recipeId, String stepDescription) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipeSection.getSteps().add(stepDescription); // Añadir el paso

        recipeSection = recipeRepository.save(recipeSection);

        return recipeMapper.toDto(recipeSection);
    }

    public RecipeDto updateStepInRecipe(Long recipeId, int stepIndex, String newStepDescription) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        if (stepIndex >= 0 && stepIndex < recipeSection.getSteps().size()) {
            recipeSection.getSteps().set(stepIndex, newStepDescription); // Reemplazar paso
            recipeSection = recipeRepository.save(recipeSection);
            return recipeMapper.toDto(recipeSection);
        } else {
            throw new StepNotFoundException("Step index out of range");
        }
    }

    public RecipeDto removeStepFromRecipe(Long recipeId, String stepDescription) {
        RecipeSection recipeSection = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        if (recipeSection.getSteps().remove(stepDescription)) {
            recipeSection = recipeRepository.save(recipeSection);
            return recipeMapper.toDto(recipeSection);
        } else {
            throw new StepNotFoundException("Step not found in recipe");
        }
    }

}
