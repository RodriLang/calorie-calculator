package com.trainerapp.calorie_calculator.specification;

import com.trainerapp.calorie_calculator.dto.filter.MealFilterParamsDto;
import com.trainerapp.calorie_calculator.model.entity.Meal;
import com.trainerapp.calorie_calculator.model.entity.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MealSpecifications {

    public static Specification<Meal> withFilters(MealFilterParamsDto params) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.name() != null && !params.name().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + params.name().toLowerCase() + "%"));
            }

            if (params.minCalories() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("calories"), params.minCalories()));
            }

            if (params.maxCalories() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("calories"), params.maxCalories()));
            }

            if (params.tagIds() != null && !params.tagIds().isEmpty()) {
                Join<Meal, Tag> tags = root.join("tagList");
                predicates.add(tags.get("id").in(params.tagIds()));
                query.distinct(true); // para evitar duplicados
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
