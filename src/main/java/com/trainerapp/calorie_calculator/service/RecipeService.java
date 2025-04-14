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
import com.trainerapp.calorie_calculator.model.entity.CustomIngredient;
import com.trainerapp.calorie_calculator.model.entity.Ingredient;
import com.trainerapp.calorie_calculator.model.entity.Meal;
import com.trainerapp.calorie_calculator.model.entity.Recipe;
import com.trainerapp.calorie_calculator.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Recipe findEntityById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(()
                -> new RecipeNotFoundException(recipeId));
    }


    public RecipeDto createRecipe(RecipeDataDto recipeDataDto) {

        Recipe recipe = new Recipe();
        recipe.setName(recipeDataDto.name());
        recipe.setShortDescription(recipeDataDto.shortDescription());
        recipe.setPreparationTime(recipeDataDto.preparationTime());
        recipe.setDifficulty(recipeDataDto.difficulty());

        // Mapear ingredientes
        for (IngredientDataDto ingredientDto : recipeDataDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipe.getIngredients().add(ingredient);
        }

        // Mapear custom ingredients
        for (CustomIngredientDataDto customDto : recipeDataDto.customIngredients()) {
            CustomIngredient customIngredient = customIngredientService.create(customDto);
            recipe.getCustomIngredients().add(customIngredient);
        }

        // Guardar los pasos
        recipe.setSteps(new ArrayList<>(recipeDataDto.steps()));

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }


    public RecipeDto updateRecipe(Long recipeId, RecipeDataDto recipeDataDto) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipe.setName(recipeDataDto.name());
        recipe.setShortDescription(recipeDataDto.shortDescription());
        recipe.setPreparationTime(recipeDataDto.preparationTime());
        recipe.setDifficulty(recipeDataDto.difficulty());

        // Limpiar y actualizar ingredientes
        recipe.getIngredients().clear();
        for (IngredientDataDto ingredientDto : recipeDataDto.ingredients()) {
            Ingredient ingredient = ingredientService.create(ingredientDto);
            recipe.getIngredients().add(ingredient);
        }

        // Limpiar y actualizar custom ingredients
        recipe.getCustomIngredients().clear();
        for (CustomIngredientDataDto customDto : recipeDataDto.customIngredients()) {
            CustomIngredient customIngredient = customIngredientService.create(customDto);
            recipe.getCustomIngredients().add(customIngredient);
        }

        // Actualizar pasos
        recipe.getSteps().clear();
        recipe.getSteps().addAll(recipeDataDto.steps());

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }


    public void deleteRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));
        recipeRepository.delete(recipe);
    }


    public RecipeDto addIngredientToRecipe(Long recipeId, IngredientDataDto ingredientDataDto) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        Ingredient ingredient = ingredientService.create(ingredientDataDto);

        recipe.getIngredients().add(ingredient);
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    public RecipeDto removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        boolean removed = recipe.getIngredients().removeIf(i -> i.getId().equals(ingredientId));

        if (!removed) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + ingredientId + " in recipe id: " + recipeId);
        }

        recipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(recipe);
    }


    public RecipeDto updateIngredientInRecipe(Long recipeId, Long ingredientId, IngredientDataDto newIngredientData) {
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

    public RecipeDto addCustomIngredient(Long recipeId, CustomIngredientDataDto customIngredientDataDto) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipe.getCustomIngredients().add(customIngredientService.create(customIngredientDataDto));
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    public RecipeDto updateCustomIngredient(Long recipeId, Long customIngredientId, CustomIngredientDataDto updatedData) {
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


    public RecipeDto removeCustomIngredient(Long recipeId, Long customIngredientId) {
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

    public RecipeDto addStepToRecipe(Long recipeId, String stepDescription) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));

        recipe.getSteps().add(stepDescription); // Añadir el paso

        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    public RecipeDto updateStepInRecipe(Long recipeId, int stepIndex, String newStepDescription) {
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

    public RecipeDto removeStepFromRecipe(Long recipeId, String stepDescription) {
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
