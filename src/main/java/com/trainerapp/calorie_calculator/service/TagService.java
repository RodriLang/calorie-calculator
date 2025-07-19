package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagService {

    Tag findOrCreateByDataDto(TagRequestDto dto);
}
