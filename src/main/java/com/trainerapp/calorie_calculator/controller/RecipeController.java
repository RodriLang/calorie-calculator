package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeDataDto;
import com.trainerapp.calorie_calculator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/{id}")
    public RecipeDto getRecipeById(@PathVariable Long id) {
        return recipeService.findById(id);
    }

    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDataDto recipeDataDto) {
        return recipeService.createRecipe(recipeDataDto);
    }

    @PutMapping("/{id}")
    public RecipeDto updateRecipe(@PathVariable Long id, @RequestBody RecipeDataDto recipeDataDto) {
        return recipeService.updateRecipe(id, recipeDataDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
