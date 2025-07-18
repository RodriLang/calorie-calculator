package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.dto.response.FoodResponseDto;
import com.trainerapp.calorie_calculator.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                MicronutrientContentMapper.class,
                NutritionalInfoMapper.class,
                TagMapper.class
        })

public interface FoodMapper {

    FoodResponseDto toDto(Food food);

    Food fromDto(FoodResponseDto foodResponseDto);

    Food toEntity(FoodRequestDto foodRequestDto);


}
