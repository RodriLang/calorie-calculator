package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import com.trainerapp.calorie_calculator.model.dto.NutritionalInfoDto;
import com.trainerapp.calorie_calculator.model.dto.create.NutritionalInfoDataDto;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;
import com.trainerapp.calorie_calculator.service.FoodService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {
        FoodService.class,
        FoodMapper.class,
        MicronutrientMapper.class,
        MicronutrientContentMapper.class,
        MeasurementUnitMapper.class
})

public interface NutritionalInfoMapper {

    @Mapping(source = "energy", target = "energyValue", qualifiedByName = "mapNutrientValueDtoToDouble")
    @Mapping(source = "carbohydrates", target = "carbohydrates", qualifiedByName = "mapNutrientValueDtoToDouble")
    @Mapping(source = "sugars", target = "sugars", qualifiedByName = "mapNutrientValueDtoToDouble")
    @Mapping(source = "protein", target = "protein", qualifiedByName = "mapNutrientValueDtoToDouble")
    @Mapping(source = "totalFat", target = "totalFat", qualifiedByName = "mapNutrientValueDtoToDouble")
    @Mapping(source = "saturatedFat", target = "saturatedFat", qualifiedByName = "mapNutrientValueDtoToDouble")
    @Mapping(source = "fiber", target = "fiber", qualifiedByName = "mapNutrientValueDtoToDouble")

    NutritionalInfo toEntity(NutritionalInfoDto dto);

    @Mapping(source = "energyValue", target = "energy", qualifiedByName = "mapEnergy")
    @Mapping(source = "carbohydrates", target = "carbohydrates", qualifiedByName = "mapGrams")
    @Mapping(source = "sugars", target = "sugars", qualifiedByName = "mapGrams")
    @Mapping(source = "protein", target = "protein", qualifiedByName = "mapGrams")
    @Mapping(source = "totalFat", target = "totalFat", qualifiedByName = "mapGrams")
    @Mapping(source = "saturatedFat", target = "saturatedFat", qualifiedByName = "mapGrams")
    @Mapping(source = "fiber", target = "fiber", qualifiedByName = "mapGrams")

    NutritionalInfoDto toDto(NutritionalInfo entity);

    
    NutritionalInfo fromDataDto(NutritionalInfoDataDto dataDto);

    @Named("mapNutrientValueDtoToDouble")
    default Double mapNutrientValueDtoToDouble(NutrientValueDto dto) {
        return dto != null ? dto.value() : null;
    }

    @Named("mapEnergy")
    default NutrientValueDto mapEnergy(Double value) {
        return value != null ? new NutrientValueDto(value, UnitType.KILOCALORIES.getAbbreviation()) : null;
    }

    @Named("mapGrams")
    default NutrientValueDto mapGrams(Double value) {
        return value != null ? new NutrientValueDto(value, UnitType.GRAMS.getAbbreviation()) : null;
    }

}

