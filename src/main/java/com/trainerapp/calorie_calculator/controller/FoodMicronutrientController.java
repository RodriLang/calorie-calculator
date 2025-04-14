package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientContentDataDto;
import com.trainerapp.calorie_calculator.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/foods")
public class FoodMicronutrientController {

    private final FoodService foodService;

    @PutMapping("/{foodId}/micronutrients")
    public ResponseEntity<FoodDto> addMicronutrients(
            @PathVariable Long foodId,
            @RequestBody List<MicronutrientContentDataDto> micronutrients) {
        return ResponseEntity.ok(foodService.addOrUpdateMicronutrients(foodId, micronutrients));
    }

    @PatchMapping("/{foodId}/micronutrient")
    public ResponseEntity<FoodDto> addMicronutrient(
            @PathVariable Long foodId,
            @RequestBody MicronutrientContentDataDto micronutrient) {
        return ResponseEntity.ok(foodService.addMicronutrient(foodId, micronutrient));
    }


    @DeleteMapping("/{foodId}/micronutrients")
    public ResponseEntity<FoodDto> removeMicronutrients(
            @PathVariable Long foodId,
            @RequestBody List<Long> micronutrientIds) {
        return ResponseEntity.ok(foodService.removeMicronutrients(foodId, micronutrientIds));
    }
}
