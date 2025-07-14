package com.trainerapp.calorie_calculator.model.dto.create;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record NutritionalInfoDataDto(

        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double energy,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double carbohydrates,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double sugars,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double protein,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double totalFat,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double saturatedFat,
        @NotNull @DecimalMin(value = "0.0", inclusive = true) Double fiber

) {
}
