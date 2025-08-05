package com.trainerapp.calorie_calculator.web.mapper;

import com.trainerapp.calorie_calculator.domain.model.Food;
import com.trainerapp.calorie_calculator.web.dto.request.FoodRequestDto;
import com.trainerapp.calorie_calculator.web.dto.response.FoodResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                MicronutrientContentDtoMapper.class,
                NutritionalInfoDtoMapper.class,
                TagDtoMapper.class
        })

public interface FoodDtoMapper {

    FoodResponseDto toDto(Food model);

    Food toModel(FoodRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Food updateFoodFromDto(FoodRequestDto dto, @MappingTarget Food.FoodBuilder builder);
}
