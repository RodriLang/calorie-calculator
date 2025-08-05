package com.trainerapp.calorie_calculator.infrastructure.persistence.entity;

import com.trainerapp.calorie_calculator.domain.enums.TagType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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

    @Column(nullable = false, unique = true)
    private UUID publicId;

    @Column(unique = true, nullable = false)
    private String label;

    @Enumerated(EnumType.STRING)
    private TagType tagType;
}
