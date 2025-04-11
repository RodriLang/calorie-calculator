package com.trainerapp.calorie_calculator.utils.mapper;

import com.trainerapp.calorie_calculator.enums.UnitType;
import com.trainerapp.calorie_calculator.model.dto.FoodDto;
import com.trainerapp.calorie_calculator.model.dto.MicronutrientContentDto;
import com.trainerapp.calorie_calculator.model.dto.NutrientValueDto;
import com.trainerapp.calorie_calculator.model.dto.NutritionalInfoDto;
import com.trainerapp.calorie_calculator.model.entity.Food;
import com.trainerapp.calorie_calculator.model.entity.MicronutrientContent;
import com.trainerapp.calorie_calculator.model.entity.NutritionalInfo;

public class FoodMapper {

    private FoodMapper() {
    }

    public static FoodDto mapToDto(Food food) {
        return new FoodDto(
                food.getName(),
                food.getFoodOrigin(),
                food.getNutritionalFunctions(),
                FoodMapper.mapToDto(food.getNutritionalInfo()),
                food.getMicronutrients().stream().map(FoodMapper::mapToDto).toList());
    }


    public static MicronutrientContentDto mapToDto(MicronutrientContent micronutrientContent) {
        double dailyPercentage = Util.formatPercentage(micronutrientContent.getAmountPerGram()
                / micronutrientContent.getMicronutrient().getDailyAmount()*100);

        return new MicronutrientContentDto(
                micronutrientContent.getMicronutrient().getName(),
                dailyPercentage,
                UnitType.PERCENTAGE.getAbbreviation(),
                micronutrientContent.getMicronutrient().getType());
    }

    public static NutritionalInfoDto mapToDto(NutritionalInfo info) {
        return new NutritionalInfoDto(
                new NutrientValueDto(info.getEnergyValue(), UnitType.KILOCALORIES.getAbbreviation()),
                new NutrientValueDto(info.getCarbohydrates(), UnitType.GRAMS.getAbbreviation()),
                new NutrientValueDto(info.getSugars(), UnitType.GRAMS.getAbbreviation()),
                new NutrientValueDto(info.getProtein(), UnitType.GRAMS.getAbbreviation()),
                new NutrientValueDto(info.getTotalFat(), UnitType.GRAMS.getAbbreviation()),
                new NutrientValueDto(info.getSaturatedFat(), UnitType.GRAMS.getAbbreviation()),
                new NutrientValueDto(info.getFiber(), UnitType.GRAMS.getAbbreviation())
        );
    }

}
