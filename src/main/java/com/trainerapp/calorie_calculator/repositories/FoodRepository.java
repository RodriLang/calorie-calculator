package com.trainerapp.calorie_calculator.repositories;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.models.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Food findByName(String name);

    List<Food> findByNutritionalInfo_Calories(int calories);

    Page<Food> findByNutritionalInfo_Calories(int calories, Pageable pageable);

    List<Food> findByNutritionalInfo_CaloriesBetween(int calories1, int calories2);

    Page<Food> findByNutritionalInfo_CaloriesBetween(int calories1, int calories2, Pageable pageable);

    Food findByNutritionalInfo_CaloriesAndName(int calories, String name);

    List<Food> findByFoodOrigin(FoodOriginType foodOriginType);

    Page<Food> findByFoodOrigin(FoodOriginType foodOriginType, Pageable pageable);
}
