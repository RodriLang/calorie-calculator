package com.trainerapp.calorie_calculator.domain.model;

import com.trainerapp.calorie_calculator.domain.enums.TagType;
import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Tag {

    private UUID publicId;

    private String label;

    private TagType tagType;
}
