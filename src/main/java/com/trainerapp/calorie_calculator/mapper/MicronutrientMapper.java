package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MicronutrientDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientDataDto;
import com.trainerapp.calorie_calculator.model.entity.Micronutrient;
import com.trainerapp.calorie_calculator.service.FoodService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        FoodService.class,
        FoodMapper.class,
        MicronutrientContentMapper.class
})
public interface MicronutrientMapper {

    Micronutrient fromDto(MicronutrientDto micronutrientDto);

    MicronutrientDto toDto(Micronutrient micronutrient);

    Micronutrient fromDataDto(MicronutrientDataDto micronutrientDataDto);
}
