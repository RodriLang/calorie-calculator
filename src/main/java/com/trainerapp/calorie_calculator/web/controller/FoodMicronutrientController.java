package com.trainerapp.calorie_calculator.web.controller;

import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.web.dto.request.MicronutrientContentRequestDto;
import com.trainerapp.calorie_calculator.application.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/foods")
public class FoodMicronutrientController {

    private final FoodService foodService;


    @PostMapping("/{foodId}/micronutrient")
    public ResponseEntity<FoodResponseDto> addMicronutrient(
            @PathVariable UUID foodId,
            @RequestBody MicronutrientContentRequestDto micronutrient) {
        FoodResponseDto foodResponseDto = foodService.addMicronutrient(foodId, micronutrient);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodResponseDto);
    }


    @PutMapping("/{foodId}/micronutrients")
    public ResponseEntity<FoodResponseDto> addMicronutrients(
            @PathVariable UUID foodId,
            @RequestBody List<MicronutrientContentRequestDto> micronutrients) {
        return ResponseEntity.ok(foodService.addOrUpdateMicronutrients(foodId, micronutrients));
    }


    @DeleteMapping("/{foodId}/micronutrient/{micronutrientId}")
    public ResponseEntity<Void> removeMicronutrient(
            @PathVariable UUID foodId,
            @PathVariable UUID micronutrientId) {
        foodService.removeMicronutrient(foodId, micronutrientId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{foodId}/micronutrients")
    public ResponseEntity<Void> removeMicronutrients(
            @PathVariable UUID foodId,
            @RequestParam List<UUID> micronutrientIds) {
        foodService.removeMicronutrients(foodId, micronutrientIds);
        return ResponseEntity.noContent().build();
    }
}
