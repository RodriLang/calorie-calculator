package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.FoodDto;
import com.trainerapp.calorie_calculator.dto.create.MeasurementUnitDataDto;
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
    public ResponseEntity<FoodDto> addMeasurementUnit(
            @PathVariable Long foodId,
            @RequestBody MeasurementUnitDataDto measurementUnit) {
        return ResponseEntity.ok(foodService.addMeasurementUnit(foodId, measurementUnit));
    }


    @DeleteMapping("/{foodId}/measurement-unit")
    public ResponseEntity<FoodDto> removeMeasurementUnit(
            @PathVariable Long foodId,
            @RequestBody Long measurementUnitId) {
        return ResponseEntity.ok(foodService.removeMeasurementUnit(foodId, measurementUnitId));
    }
}
