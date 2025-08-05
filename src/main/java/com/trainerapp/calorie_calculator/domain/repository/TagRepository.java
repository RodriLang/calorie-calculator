package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.domain.enums.TagType;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

            Optional<TagEntity> findByLabelAndTagType(String label, TagType type);

}
