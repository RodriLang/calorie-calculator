package com.trainerapp.calorie_calculator.domain.repository;

import com.trainerapp.calorie_calculator.infrastructure.persistence.entity.SeasoningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasoningRepository extends JpaRepository<SeasoningEntity, Long> {

    public SeasoningEntity findEntityById(Long id);
}
