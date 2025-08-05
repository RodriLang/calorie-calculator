package com.trainerapp.calorie_calculator.application.service.impl;

import com.trainerapp.calorie_calculator.application.exception.TagNotFoundException;
import com.trainerapp.calorie_calculator.domain.model.Tag;
import com.trainerapp.calorie_calculator.infrastructure.persistence.mapper.TagEntityMapper;
import com.trainerapp.calorie_calculator.web.dto.request.TagRequestDto;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.TagEntity;
import com.trainerapp.calorie_calculator.domain.repository.TagRepository;
import com.trainerapp.calorie_calculator.application.service.TagService;
import com.trainerapp.calorie_calculator.web.mapper.TagDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagEntityMapper tagEntityMapper;
    private final TagDtoMapper tagDtoMapper;


    @Override
    public Tag findOrCreateByDataDto(TagRequestDto dto) {
        TagEntity tagEntity = tagRepository.findByLabelAndTagType(dto.label(), dto.tagType())
                .orElseGet(() ->
                        tagRepository.save(
                                tagEntityMapper.toEntity(
                                        tagDtoMapper.toModel(dto))));

        return tagEntityMapper.toModel(tagEntity);
    }

    @Override
    public Tag findById(Long id) {
        return tagEntityMapper.toModel(tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException(id)));
    }

}

