package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.FoodEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.RecipeEntity;
import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity> {

    Optional<RecipeEntity> findByPublicId(UUID publicId);

    List<RecipeEntity> findByTagListIn(List<TagEntity> tagList);

    Page<RecipeEntity> findAll(Pageable pageable);

    Page<RecipeEntity> findByTagListIn(List<TagEntity> tags, Pageable pageable);

}
