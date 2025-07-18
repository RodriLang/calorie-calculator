package com.trainerapp.calorie_calculator.mapper.impl;

import com.trainerapp.calorie_calculator.enums.NutrientType;
import com.trainerapp.calorie_calculator.mapper.NutrientValueMapper;
import com.trainerapp.calorie_calculator.mapper.NutritionalInfoMapper;
import com.trainerapp.calorie_calculator.dto.response.NutritionalInfoResponseDto;
import com.trainerapp.calorie_calculator.dto.request.NutritionalInfoRequestDto;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NutritionalInfoMapperImplementation implements NutritionalInfoMapper {

    private final NutrientValueMapper nutrientValueMapper;

    public NutritionalInfo toEntity(NutritionalInfoResponseDto dto) {

        return new NutritionalInfo(
                dto.energy().value(),
                dto.carbohydrates().value(),
                dto.sugars().value(),
                dto.protein().value(),
                dto.totalFat().value(),
                dto.saturatedFat().value(),
                dto.fiber().value()
        );
    }

    public NutritionalInfoResponseDto toDto(NutritionalInfo entity) {
        return new NutritionalInfoResponseDto(
                nutrientValueMapper.map(entity.getEnergyValue(), NutrientType.ENERGY),
                nutrientValueMapper.map(entity.getCarbohydrates(), NutrientType.CARBOHYDRATES),
                nutrientValueMapper.map(entity.getSugars(), NutrientType.SUGARS),
                nutrientValueMapper.map(entity.getProtein(), NutrientType.PROTEIN),
                nutrientValueMapper.map(entity.getTotalFat(), NutrientType.TOTAL_FAT),
                nutrientValueMapper.map(entity.getSaturatedFat(), NutrientType.SATURATED_FAT),
                nutrientValueMapper.map(entity.getFiber(), NutrientType.FIBER)
        );
    }


    public NutritionalInfo fromDataDto(NutritionalInfoRequestDto dataDto) {

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

}