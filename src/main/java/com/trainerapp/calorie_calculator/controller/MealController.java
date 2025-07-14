package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.MealCardDto;
import com.trainerapp.calorie_calculator.dto.MealDto;
import com.trainerapp.calorie_calculator.dto.RecipeDto;
import com.trainerapp.calorie_calculator.dto.create.MealDataDto;
import com.trainerapp.calorie_calculator.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calorie-calculator/meals")
public class MealController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<Page<MealCardDto>> getAllMealCards(@PageableDefault(
            sort = "name", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        return ResponseEntity.ok(mealService.getAllMealCards(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDto> getMealById(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @PostMapping
    public ResponseEntity<MealDto> createMeal(@RequestBody MealDataDto mealDataDto) {
        return ResponseEntity.ok(mealService.createMeal(mealDataDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealDto> updateMeal(@PathVariable Long id, @RequestBody MealDataDto mealDataDto) {
        return ResponseEntity.ok(mealService.updateMeal(id, mealDataDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.removeMeal(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{mealId}/recipes")
    public ResponseEntity<MealDto> addRecipeToMeal(@PathVariable Long mealId, @RequestBody RecipeDto recipeDto) {
        return ResponseEntity.ok(mealService.addRecipeToMeal(mealId, recipeDto));
    }

    @DeleteMapping("/{mealId}/recipes/{recipeId}")
    public ResponseEntity<MealDto> removeRecipeFromMeal(@PathVariable Long mealId, @PathVariable Long recipeId) {
        return ResponseEntity.ok(mealService.removeRecipeFromMeal(mealId, recipeId));
    }
}