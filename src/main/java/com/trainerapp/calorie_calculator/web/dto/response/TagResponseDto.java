package com.trainerapp.calorie_calculator.web.dto.response;


import java.util.UUID;

public record TagResponseDto(

        UUID id,

        String label,

        String tagType
) {
}
