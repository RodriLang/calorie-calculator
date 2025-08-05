package com.trainerapp.calorie_calculator.web.controller;

import com.trainerapp.calorie_calculator.web.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.RecipeRequestDto;
import com.trainerapp.calorie_calculator.application.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public RecipeResponseDto getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public RecipeResponseDto createRecipe(@RequestBody RecipeRequestDto recipeRequestDto) {
        return recipeService.createRecipe(recipeRequestDto);
    }

    @PutMapping("/{id}")
    public RecipeResponseDto updateRecipe(@PathVariable Long id, @RequestBody RecipeRequestDto recipeRequestDto) {
        return recipeService.updateRecipe(id, recipeRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
