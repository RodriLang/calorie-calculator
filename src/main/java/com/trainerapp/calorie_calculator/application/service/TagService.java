package com.trainerapp.calorie_calculator.application.service;

import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;


public interface TagService {

    Tag findOrCreateByDataDto(TagRequestDto dto);
    Tag findById(Long id);

}
