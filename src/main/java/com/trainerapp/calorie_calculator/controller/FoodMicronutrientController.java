package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/foods")
public class FoodMicronutrientController {

    private final FoodService foodService;


    @PostMapping("/{foodId}/micronutrient")
    public ResponseEntity<FoodResponseDto> addMicronutrient(
            @PathVariable Long foodId,
            @RequestBody MicronutrientContentRequestDto micronutrient) {
        FoodResponseDto foodResponseDto = foodService.addMicronutrient(foodId, micronutrient);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodResponseDto);
    }


    @PutMapping("/{foodId}/micronutrients")
    public ResponseEntity<FoodResponseDto> addMicronutrients(
            @PathVariable Long foodId,
            @RequestBody List<MicronutrientContentRequestDto> micronutrients) {
        return ResponseEntity.ok(foodService.addOrUpdateMicronutrients(foodId, micronutrients));
    }


    @DeleteMapping("/{foodId}/micronutrient/{micronutrientId}")
    public ResponseEntity<Void> removeMicronutrient(
            @PathVariable Long foodId,
            @PathVariable Long micronutrientId) {
        foodService.removeMicronutrient(foodId, micronutrientId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{foodId}/micronutrients")
    public ResponseEntity<Void> removeMicronutrients(
            @PathVariable Long foodId,
            @RequestParam List<Long> micronutrientIds) {
        foodService.removeMicronutrients(foodId, micronutrientIds);
        return ResponseEntity.noContent().build();
    }
}
