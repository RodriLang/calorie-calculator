package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.TagType;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Tag {

    private String label;

    private TagType tagType;
}
