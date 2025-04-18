package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeCardDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calorie-calculator/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Page<RecipeCardDto>> getAllRecipeCards(@PageableDefault(
            page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(recipeService.getAllMealCards(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDataDto recipeDataDto) {
        return ResponseEntity.ok(recipeService.createRecipe(recipeDataDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long id, @RequestBody RecipeDataDto recipeDataDto) {
        return ResponseEntity.ok(recipeService.updateRecipes(id, recipeDataDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.removeRecipes(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{recipeId}/recipe-sections/{sectionId}")
    public ResponseEntity<RecipeDto> addSectionToRecipe(@PathVariable Long recipeId, @RequestBody RecipeSectionDto recipeSectionDto) {
        return ResponseEntity.ok(recipeService.addRecipeToMeal(recipeId, recipeSectionDto));
    }

    @DeleteMapping("/{recipeId}/recipe-sections/{sectionId}")
    public ResponseEntity<RecipeDto> removeRecipeFromRecipe(@PathVariable Long sectionId, @PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.removeRecipeFromMeal(sectionId, recipeId));
    }
}