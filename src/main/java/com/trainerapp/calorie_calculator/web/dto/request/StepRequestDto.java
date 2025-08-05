package com.trainerapp.calorie_calculator.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StepRequestDto(

        Integer stepNumber,

        @NotBlank
        String label,

        @NotBlank
        String instructions
) {
}
