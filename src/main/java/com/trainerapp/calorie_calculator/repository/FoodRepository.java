package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.model.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Food findByName(String name);

    List<Food> findByNutritionalInfo_EnergyValue(int energyValue);

    Page<Food> findByNutritionalInfo_EnergyValue(int energyValue, Pageable pageable);

    List<Food> findByNutritionalInfo_EnergyValueBetween(int energyValue1, int energyValue2);

    Page<Food> findByNutritionalInfo_EnergyValueBetween(int energyValue1, int energyValue2, Pageable pageable);

    Food findByNutritionalInfo_EnergyValueAndName(int energyValue, String name);

    List<Food> findByFoodOrigin(FoodOriginType foodOriginType);

    Page<Food> findByFoodOrigin(FoodOriginType foodOriginType, Pageable pageable);
}
