package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;

import java.util.UUID;


public interface TagService {

    Tag findOrCreateByDataDto(TagRequestDto dto);
    Tag findById(UUID id);

}
