package com.trainerapp.calorie_calculator.controllers;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.models.Food;
import com.trainerapp.calorie_calculator.services.FoodService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/save")
    public ResponseEntity<Food> save(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.save(food));
    }

    @GetMapping()
    public ResponseEntity<List<Food>> getAll() {
        return ResponseEntity.ok(foodService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getById(@RequestParam int id) {
        return ResponseEntity.ok(foodService.getById(id));
    }

    @GetMapping("/origin")
  public ResponseEntity<List<Food>> getByFoodOrigin(@RequestParam FoodOriginType origin) {
        return ResponseEntity.ok(foodService.findByFoodOrigin(origin));
    }


    @GetMapping("/calories")
    public ResponseEntity<List<Food>> getByCalories(@RequestParam int calories) {
        return ResponseEntity.ok(foodService.findByCalories(calories));
    }

    @GetMapping("/calories-beetwen")
    public ResponseEntity<List<Food>> getByCaloriesBeetwen(
            @RequestParam int minCalories,
            @RequestParam int maxCalories){
        return ResponseEntity.ok(foodService.findByCaloriesBetween(minCalories, maxCalories));
    }



}
