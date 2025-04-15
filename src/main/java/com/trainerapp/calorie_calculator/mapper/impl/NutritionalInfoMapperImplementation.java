package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.NutrientType;
import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.mapper.NutrientValueMapper;
import com.trainerapp.calorie_calculator.mapper.NutritionalInfoMapper;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import com.trainerapp.calorie_calculator.model.dto.NutritionalInfoDto;
import com.trainerapp.calorie_calculator.model.dto.create.NutritionalInfoDataDto;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NutritionalInfoMapperImplementation implements NutritionalInfoMapper {

    private final NutrientValueMapper nutrientValueMapper;

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
                .energy(nutrientValueMapper.map(entity.getEnergyValue(), NutrientType.ENERGY))
                .carbohydrates(nutrientValueMapper.map(entity.getCarbohydrates(), NutrientType.CARBOHYDRATES))
                .sugars(nutrientValueMapper.map(entity.getSugars(), NutrientType.SUGARS))
                .protein(nutrientValueMapper.map(entity.getProtein(), NutrientType.PROTEIN))
                .totalFat(nutrientValueMapper.map(entity.getTotalFat(),NutrientType.TOTAL_FAT))
                .saturatedFat(nutrientValueMapper.map(entity.getSaturatedFat(), NutrientType.SATURATED_FAT))
                .fiber(nutrientValueMapper.map(entity.getFiber(), NutrientType.FIBER))
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

   /* private NutrientValueDto mapEnergy(Double value) {
        return value != null ? new NutrientValueDto(value, UnitType.KILOCALORIES.getAbbreviation()) : null;
    }

    private NutrientValueDto mapGrams(Double value) {
        return value != null ? new NutrientValueDto(value, UnitType.GRAMS.getAbbreviation()) : null;
    }
*/
}