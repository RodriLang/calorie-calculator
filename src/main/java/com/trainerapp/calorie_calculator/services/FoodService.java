package com.trainerapp.calorie_calculator.services;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.models.Food;
import com.trainerapp.calorie_calculator.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    public Food getById(long id) {
        return foodRepository.getReferenceById(id);
    }

    public Food save(Food food) {
        return foodRepository.save(food);
    }

    public void deleteById(long id) {
        foodRepository.deleteById(id);
    }

    public void delete(Food food) {
        foodRepository.delete(food);
    }

    public void delete(List<Food> foods) {
        foodRepository.deleteAll(foods);
    }

    public void update(Food food) {
        foodRepository.save(food);
    }

    public void update(List<Food> foods) {
        foodRepository.saveAll(foods);
    }
    public List<Food> findByCalories(int calories) {
        return foodRepository.findByNutritionalInfo_Calories(calories);
    }

    public List<Food> findByCaloriesBetween(int calories1, int calories2) {
        return foodRepository.findByNutritionalInfo_CaloriesBetween(calories1, calories2);
    }


    public Food findByCaloriesAndName(int calories, String name) {
        return foodRepository.findByNutritionalInfo_CaloriesAndName(calories, name);

    }

    public List<Food> findByFoodOrigin(FoodOriginType foodOriginType) {
        return foodRepository.findByFoodOrigin(foodOriginType);
    }

}
