package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.CustomIngredientRequestDto;
import com.trainerapp.calorie_calculator.dto.request.IngredientRequestDto;
import com.trainerapp.calorie_calculator.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.exception.CustomIngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.IngredientNotFoundException;
import com.trainerapp.calorie_calculator.exception.RecipeNotFoundException;
import com.trainerapp.calorie_calculator.exception.StepNotFoundException;
import com.trainerapp.calorie_calculator.mapper.RecipeMapper;
import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
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

    public RecipeResponseDto saveRecipe(RecipeRequestDto recipeRequestDto) {
        return recipeMapper.toDto(recipeRepository.save(recipeMapper.fromDataDto(recipeRequestDto)));
    }

    public List<RecipeResponseDto> getRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::toDto)
                .toList();
    }

    public RecipeResponseDto findById(Long recipeId) {
        return recipeMapper.toDto(recipeRepository.findById(recipeId).orElseThrow(()
                -> new RecipeNotFoundException(recipeId)));
    }

    public Recipe findEntityById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()
                -> new RecipeNotFoundException(recipeId));
    }


    public RecipeResponseDto createRecipe(RecipeRequestDto recipeRequestDto) {

        Recipe recipe = new Recipe();
        recipe.setName(recipeRequestDto.name());
        recipe.setShortDescription(recipeRequestDto.shortDescription());
        recipe.setPreparationTime(recipeRequestDto.preparationTime());
        recipe.setDifficulty(recipeRequestDto.difficulty());

        // Mapear ingredientes
        for (IngredientRequestDto ingredientDto : recipeRequestDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipe.getIngredients().add(ingredient);
        }

        // Mapear custom ingredients
        for (CustomIngredientRequestDto customDto : recipeRequestDto.customIngredients()) {
            CustomIngredient customIngredient = customIngredientService.create(customDto);
            recipe.getCustomIngredients().add(customIngredient);
        }

        // Guardar los pasos
        recipe.setSteps(new ArrayList<>(recipeRequestDto.steps()));

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }


    public RecipeResponseDto updateRecipe(Long recipeId, RecipeRequestDto recipeRequestDto) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipe.setName(recipeRequestDto.name());
        recipe.setShortDescription(recipeRequestDto.shortDescription());
        recipe.setPreparationTime(recipeRequestDto.preparationTime());
        recipe.setDifficulty(recipeRequestDto.difficulty());

        // Limpiar y actualizar ingredientes
        recipe.getIngredients().clear();
        for (IngredientRequestDto ingredientDto : recipeRequestDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipe.getIngredients().add(ingredient);
        }

        // Limpiar y actualizar custom ingredients
        recipe.getCustomIngredients().clear();
        for (CustomIngredientRequestDto customDto : recipeRequestDto.customIngredients()) {
            CustomIngredient customIngredient = customIngredientService.create(customDto);
            recipe.getCustomIngredients().add(customIngredient);
        }

        // Actualizar pasos
        recipe.getSteps().clear();
        recipe.getSteps().addAll(recipeRequestDto.steps());

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }


    public void deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));
        recipeRepository.delete(recipe);
    }


    public RecipeResponseDto addIngredientToRecipe(Long recipeId, IngredientRequestDto ingredientRequestDto) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Ingredient ingredient = ingredientService.create(ingredientRequestDto);

        recipe.getIngredients().add(ingredient);
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    public RecipeResponseDto removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        boolean removed = recipe.getIngredients().removeIf(i -> i.getId().equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipe id: " + recipeId);
        }

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(recipe);
    }


    public RecipeResponseDto updateIngredientInRecipe(Long recipeId, Long ingredientId, IngredientRequestDto newIngredientData) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Ingredient ingredient = recipe.getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .findFirst()
                .orElseThrow(() -> new IngredientNotFoundException(ingredientId));

        // Actualizar usando IngredientService
        ingredientService.update(ingredient, newIngredientData);

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(recipe);
    }


//Custom Ingredients

    public RecipeResponseDto addCustomIngredient(Long recipeId, CustomIngredientRequestDto customIngredientRequestDto) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipe.getCustomIngredients().add(customIngredientService.create(customIngredientRequestDto));
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    public RecipeResponseDto updateCustomIngredient(Long recipeId, Long customIngredientId, CustomIngredientRequestDto updatedData) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        CustomIngredient ingredient = recipe.getCustomIngredients().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        // Actualizar campos
        customIngredientService.update(ingredient, updatedData);

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(recipe);
    }


    public RecipeResponseDto removeCustomIngredient(Long recipeId, Long customIngredientId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        CustomIngredient ingredient = recipe.getCustomIngredients().stream()
                .filter(i -> i.getId().equals(customIngredientId))
                .findFirst()
                .orElseThrow(() -> new CustomIngredientNotFoundException(customIngredientId));

        recipe.getCustomIngredients().remove(ingredient);

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(recipe);
    }


//Steps

    public RecipeResponseDto addStepToRecipe(Long recipeId, String stepDescription) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipe.getSteps().add(stepDescription); // Añadir el paso

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    public RecipeResponseDto updateStepInRecipe(Long recipeId, int stepIndex, String newStepDescription) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        if (stepIndex >= 0 && stepIndex < recipe.getSteps().size()) {
            recipe.getSteps().set(stepIndex, newStepDescription); // Reemplazar paso
            recipe = recipeRepository.save(recipe);
            return recipeMapper.toDto(recipe);
        } else {
            throw new StepNotFoundException("Step index out of range");
        }
    }

    public RecipeResponseDto removeStepFromRecipe(Long recipeId, String stepDescription) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        if (recipe.getSteps().remove(stepDescription)) {
            recipe = recipeRepository.save(recipe);
            return recipeMapper.toDto(recipe);
        } else {
            throw new StepNotFoundException("Step not found in recipe");
        }
    }

}
