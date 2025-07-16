package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
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
    public ResponseEntity<FoodResponseDto> addMicronutrients(
            @PathVariable Long foodId,
            @RequestBody List<MicronutrientContentRequestDto> micronutrients) {
        return ResponseEntity.ok(foodService.addOrUpdateMicronutrients(foodId, micronutrients));
    }

    @PatchMapping("/{foodId}/micronutrient")
    public ResponseEntity<FoodResponseDto> addMicronutrient(
            @PathVariable Long foodId,
            @RequestBody MicronutrientContentRequestDto micronutrient) {
        return ResponseEntity.ok(foodService.addMicronutrient(foodId, micronutrient));
    }


    @DeleteMapping("/{foodId}/micronutrients")
    public ResponseEntity<FoodResponseDto> removeMicronutrients(
            @PathVariable Long foodId,
            @RequestBody List<Long> micronutrientIds) {
        return ResponseEntity.ok(foodService.removeMicronutrients(foodId, micronutrientIds));
    }
}
