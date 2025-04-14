package com.trainerapp.calorie_calculator.model.dto.create;

import com.trainerapp.calorie_calculator.enums.TagType;

public record CreateTagDto(
        String label,
        TagType type) {
}
