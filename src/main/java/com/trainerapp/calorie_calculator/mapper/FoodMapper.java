package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                MicronutrientContentMapper.class,
                NutritionalInfoMapper.class,
                TagMapper.class
        })

public interface FoodMapper {

    FoodResponseDto toDto(Food food);

    Food toEntity(FoodRequestDto foodRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFoodFromDto(FoodRequestDto dto, @MappingTarget Food food);
}
