package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.MicronutrientContentDto;
import com.trainerapp.calorie_calculator.model.dto.create.MicronutrientContentDataDto;
import com.trainerapp.calorie_calculator.model.entity.MicronutrientContent;
import com.trainerapp.calorie_calculator.service.FoodService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        FoodService.class,
        FoodMapper.class,
        MicronutrientMapper.class
})
public interface MicronutrientContentMapper {

    MicronutrientContent fromDto(MicronutrientContentDto micronutrientContentDto);

    MicronutrientContentDto toDto(MicronutrientContent micronutrientContent);

    MicronutrientContent fromDataDto(MicronutrientContentDataDto micronutrientContentDataDto);
}
