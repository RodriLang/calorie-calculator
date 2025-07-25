package com.trainerapp.calorie_calculator.repository;

import com.trainerapp.calorie_calculator.enums.TagType;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

            Optional<Tag> findByLabelAndTagType(String label, TagType type);

}
