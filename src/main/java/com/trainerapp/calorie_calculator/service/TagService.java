package com.trainerapp.calorie_calculator.service;

import com.trainerapp.calorie_calculator.mapper.TagMapper;
import com.trainerapp.calorie_calculator.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import com.trainerapp.calorie_calculator.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }


    public Tag findOrCreateByDataDto(TagRequestDto dto) {
        return tagRepository.findByLabelAndTagType(dto.label(), dto.tagType())
                .orElseGet(() -> tagRepository.save(tagMapper.fromDataDto(dto)));
    }
}
