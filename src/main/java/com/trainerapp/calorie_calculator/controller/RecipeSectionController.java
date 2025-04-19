package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.RecipeSectionDto;
import com.trainerapp.calorie_calculator.model.dto.create.RecipeSectionDataDto;
import com.trainerapp.calorie_calculator.service.RecipeSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeSectionController {

    private final RecipeSectionService recipeSectionService;

    @GetMapping
    public List<RecipeSectionDto> getAllRecipes() {
        return recipeSectionService.getRecipes();
    }

    @GetMapping("/{id}")
    public RecipeSectionDto getRecipeById(@PathVariable Long id) {
        return recipeSectionService.findById(id);
    }

    @PostMapping
    public RecipeSectionDto createRecipe(@RequestBody RecipeSectionDataDto recipeSectionDataDto) {
        return recipeSectionService.createRecipe(recipeSectionDataDto);
    }

    @PutMapping("/{id}")
    public RecipeSectionDto updateRecipe(@PathVariable Long id, @RequestBody RecipeSectionDataDto recipeSectionDataDto) {
        return recipeSectionService.updateRecipe(id, recipeSectionDataDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeSectionService.deleteRecipe(id);
    }
}
