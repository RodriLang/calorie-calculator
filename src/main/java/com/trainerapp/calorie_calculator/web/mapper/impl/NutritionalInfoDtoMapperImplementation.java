package com.trainerapp.calorie_calculator.web.mapper.impl;

import com.trainerapp.calorie_calculator.domain.enums.NutrientType;
import com.trainerapp.calorie_calculator.domain.model.NutritionalInfo;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.NutritionalInfoEntity;
import com.trainerapp.calorie_calculator.web.dto.request.NutritionalInfoRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.NutritionalInfoResponseDto;
import com.trainerapp.calorie_calculator.web.mapper.NutrientValueDtoMapper;
import com.trainerapp.calorie_calculator.web.mapper.NutritionalInfoDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NutritionalInfoDtoMapperImplementation implements NutritionalInfoDtoMapper {

    private final NutrientValueDtoMapper nutrientValueDtoMapper;

    public NutritionalInfoEntity toModel(NutritionalInfoResponseDto dto) {

        return new NutritionalInfoEntity(
                dto.energy().value(),
                dto.carbohydrates().value(),
                dto.sugars().value(),
                dto.protein().value(),
                dto.totalFat().value(),
                dto.saturatedFat().value(),
                dto.fiber().value()
        );
    }

    public NutritionalInfoResponseDto toDto(NutritionalInfo model) {
        return new NutritionalInfoResponseDto(
                nutrientValueDtoMapper.map(model.getEnergyValue(), NutrientType.ENERGY),
                nutrientValueDtoMapper.map(model.getCarbohydrates(), NutrientType.CARBOHYDRATES),
                nutrientValueDtoMapper.map(model.getSugars(), NutrientType.SUGARS),
                nutrientValueDtoMapper.map(model.getProtein(), NutrientType.PROTEIN),
                nutrientValueDtoMapper.map(model.getTotalFat(), NutrientType.TOTAL_FAT),
                nutrientValueDtoMapper.map(model.getSaturatedFat(), NutrientType.SATURATED_FAT),
                nutrientValueDtoMapper.map(model.getFiber(), NutrientType.FIBER)
        );
    }


    public NutritionalInfo toModel(NutritionalInfoRequestDto dataDto) {

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

    @Override
    public NutritionalInfo updateFromDto(NutritionalInfoRequestDto dto, NutritionalInfo model) {
        if (dto == null) {
            return model;
        }

        return model.toBuilder()
                .energyValue(getValueOrDefault(dto.energy(), model.getEnergyValue()))
                .carbohydrates(getValueOrDefault(dto.carbohydrates(), model.getCarbohydrates()))
                .sugars(getValueOrDefault(dto.sugars(), model.getSugars()))
                .protein(getValueOrDefault(dto.protein(), model.getProtein()))
                .totalFat(getValueOrDefault(dto.totalFat(), model.getTotalFat()))
                .saturatedFat(getValueOrDefault(dto.saturatedFat(), model.getSaturatedFat()))
                .fiber(getValueOrDefault(dto.fiber(), model.getFiber()))
                .build();

    }

    private double getValueOrDefault(Double dtoValue, double modelValue) {
        return dtoValue != null ? dtoValue : modelValue;
    }

}