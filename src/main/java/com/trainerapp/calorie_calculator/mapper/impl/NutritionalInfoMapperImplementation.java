package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.mapper.NutritionalInfoMapper;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import com.trainerapp.calorie_calculator.model.dto.NutritionalInfoDto;
import com.trainerapp.calorie_calculator.model.dto.create.NutritionalInfoDataDto;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;
import org.springframework.stereotype.Component;


@Component
public class NutritionalInfoMapperImplementation implements NutritionalInfoMapper {

    public NutritionalInfo toEntity(NutritionalInfoDto dto) {
        return NutritionalInfo.builder()
                .energyValue(dto.energy().value())
                .carbohydrates(dto.carbohydrates().value())
                .sugars(dto.sugars().value())
                .protein(dto.protein().value())
                .totalFat(dto.totalFat().value())
                .saturatedFat(dto.saturatedFat().value())
                .fiber(dto.fiber().value())
                .build();
    }

    public NutritionalInfoDto toDto(NutritionalInfo entity) {
        return NutritionalInfoDto.builder()
                .energy(mapEnergy(entity.getEnergyValue()))
                .carbohydrates(mapGrams(entity.getCarbohydrates()))
                .sugars(mapGrams(entity.getSugars()))
                .protein(mapGrams(entity.getProtein()))
                .totalFat(mapGrams(entity.getTotalFat()))
                .saturatedFat(mapGrams(entity.getSaturatedFat()))
                .fiber(mapGrams(entity.getFiber()))
                .build();
    }


    public NutritionalInfo fromDataDto(NutritionalInfoDataDto dataDto) {
        return NutritionalInfo.builder()
                .energyValue(dataDto.energy())
                .carbohydrates(dataDto.carbohydrates())
                .sugars(dataDto.sugars())
                .protein(dataDto.protein())
                .totalFat(dataDto.totalFat())
                .saturatedFat(dataDto.saturatedFat())
                .fiber(dataDto.fiber())
                .build();
    }

    private NutrientValueDto mapEnergy(Double value) {
        return value != null ? new NutrientValueDto(value, UnitType.KILOCALORIES.getAbbreviation()) : null;
    }

    private NutrientValueDto mapGrams(Double value) {
        return value != null ? new NutrientValueDto(value, UnitType.GRAMS.getAbbreviation()) : null;
    }

}