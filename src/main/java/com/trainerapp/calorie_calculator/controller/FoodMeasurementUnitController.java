package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/foods")
public class FoodMeasurementUnitController {

    private final FoodService foodService;


    @PutMapping("/{foodId}/measurement-unit")
    public ResponseEntity<FoodResponseDto> addMeasurementUnit(
            @PathVariable Long foodId,
            @RequestBody MeasurementUnitRequestDto measurementUnit) {
        return ResponseEntity.ok(foodService.addMeasurementUnit(foodId, measurementUnit));
    }


    @DeleteMapping("/{foodId}/measurement-unit")
    public ResponseEntity<FoodResponseDto> removeMeasurementUnit(
            @PathVariable Long foodId,
            @RequestBody Long measurementUnitId) {
        return ResponseEntity.ok(foodService.removeMeasurementUnit(foodId, measurementUnitId));
    }
}
