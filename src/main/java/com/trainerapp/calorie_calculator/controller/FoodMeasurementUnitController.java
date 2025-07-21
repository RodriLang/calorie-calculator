package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.MeasurementUnitRequestDto;
import com.trainerapp.calorie_calculator.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/measurement-units")
public class FoodMeasurementUnitController {

    private final FoodService foodService;


    @PutMapping()
    public ResponseEntity<FoodResponseDto> addMeasurementUnit(
            @RequestBody MeasurementUnitRequestDto measurementUnit) {
        return ResponseEntity.ok(foodService.addMeasurementUnit(measurementUnit));
    }


    @DeleteMapping()
    public ResponseEntity<FoodResponseDto> removeMeasurementUnit(
            @RequestBody Long measurementUnitId) {
        return ResponseEntity.ok(foodService.removeMeasurementUnit(measurementUnitId));
    }
}
