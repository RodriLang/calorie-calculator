package com.trainerapp.calorie_calculator.controller;

import com.trainerapp.calorie_calculator.model.dto.MicronutrientDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientDataDto;
import com.trainerapp.calorie_calculator.service.MicronutrientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calorie-calculator/micronutrients")
public class MicronutrientController {

    private final MicronutrientService micronutrientService;

    @PostMapping
    public ResponseEntity<MicronutrientDto> save(@RequestBody MicronutrientDataDto micronutrientData) {
        return ResponseEntity.ok(micronutrientService.createMicronutrient(micronutrientData));
    }

    @GetMapping
    public ResponseEntity<List<MicronutrientDto>> getAll() {
        return ResponseEntity.ok(micronutrientService.getAllMicronutrients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MicronutrientDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(micronutrientService.getMicronutrientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MicronutrientDto> update(@PathVariable Long id,
                                                   @RequestBody MicronutrientDataDto micronutrientData) {
        return ResponseEntity.ok(micronutrientService.update(id, micronutrientData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        micronutrientService.deleteMicronutrientById(id);
        return ResponseEntity.noContent().build();
    }
}