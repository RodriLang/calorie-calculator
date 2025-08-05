package com.trainerapp.calorie_calculator.infrastructure.persistence.entity;

import com.trainerapp.calorie_calculator.domain.enums.TagType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String label;
    @Enumerated(EnumType.STRING)
    private TagType tagType;
}
