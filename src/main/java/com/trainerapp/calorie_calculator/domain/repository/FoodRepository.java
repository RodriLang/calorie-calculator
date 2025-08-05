package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.domain.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

    Optional<FoodEntity> findByPublicId(UUID publicId);

    List<FoodEntity> findByNutritionalInfo_EnergyValue(int energyValue);

    Page<FoodEntity> findByNutritionalInfo_EnergyValue(int energyValue, Pageable pageable);

    List<FoodEntity> findByNutritionalInfo_EnergyValueBetween(int energyValue1, int energyValue2);

    Page<FoodEntity> findByNutritionalInfo_EnergyValueBetween(int energyValue1, int energyValue2, Pageable pageable);

    FoodEntity findByNutritionalInfo_EnergyValueAndName(int energyValue, String name);

    List<FoodEntity> findByFoodOrigin(FoodOriginType foodOriginType);

    Page<FoodEntity> findByFoodOrigin(FoodOriginType foodOriginType, Pageable pageable);
}
