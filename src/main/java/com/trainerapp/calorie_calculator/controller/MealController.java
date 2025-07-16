package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.filter.MealFilterParamsDto;
import com.trainerapp.calorie_calculator.dto.response.MealCardResponseDto;
import com.trainerapp.calorie_calculator.dto.response.MealResponseDto;
import com.trainerapp.calorie_calculator.dto.response.RecipeResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MealRequestDto;
import com.trainerapp.calorie_calculator.service.MealService;
import com.trainerapp.calorie_calculator.validations.OnCreate;
import com.trainerapp.calorie_calculator.validations.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/calorie-calculator/meals")
public class MealController {

    private final MealService mealService;

    @GetMapping()
    public ResponseEntity<Page<MealCardResponseDto>> getFilteredMeals(
            @RequestParam(required = false) List<Long> tagIds,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minCalories,
            @RequestParam(required = false) Double maxCalories,
            @PageableDefault(
                    sort = "name", direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        MealFilterParamsDto params = new MealFilterParamsDto(tagIds, name, minCalories, maxCalories);

        return ResponseEntity.ok(mealService.filterMealCards(params, pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getMealById(
            @PathVariable Long id) {

        return ResponseEntity.ok(mealService.getMealById(id));
    }

    @PostMapping
    public ResponseEntity<MealResponseDto> createMeal(
            @RequestBody @Validated(OnCreate.class) MealRequestDto mealRequestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mealService.createMeal(mealRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealResponseDto> updateMeal(
            @PathVariable Long id,
            @RequestBody @Validated(OnUpdate.class) MealRequestDto mealRequestDto) {
        return ResponseEntity.ok(mealService.updateMeal(id, mealRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(
            @PathVariable Long id)
    {
        mealService.removeMeal(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{mealId}/recipes")
    public ResponseEntity<MealResponseDto> addRecipeToMeal(
            @PathVariable Long mealId,
            @RequestBody RecipeResponseDto recipeResponseDto) {

        return ResponseEntity.ok(mealService.addRecipeToMeal(mealId, recipeResponseDto));
    }

    @DeleteMapping("/{mealId}/recipes/{recipeId}")
    public ResponseEntity<MealResponseDto> removeRecipeFromMeal(@PathVariable Long mealId, @PathVariable Long recipeId) {
        return ResponseEntity.ok(mealService.removeRecipeFromMeal(mealId, recipeId));
    }
}