package com.trainerapp.calorie_calculator.model.entity;

import com.trainerapp.calorie_calculator.enums.TagType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String label;
    @Enumerated(EnumType.STRING)
    private TagType tagType;
}
