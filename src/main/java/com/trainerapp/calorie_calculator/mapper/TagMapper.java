package com.trainerapp.calorie_calculator.mapper;

import com.trainerapp.calorie_calculator.model.dto.TagDto;
import com.trainerapp.calorie_calculator.model.dto.create.TagDataDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.service.FoodService;
import com.trainerapp.calorie_calculator.service.MealService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        FoodService.class,
        MealService.class,
        MealMapper.class,
        TagMapper.class
})

public interface TagMapper {

    TagDto toDto(Tag tag);

    Tag fromDataDto(TagDataDto tagDataDto);

}
