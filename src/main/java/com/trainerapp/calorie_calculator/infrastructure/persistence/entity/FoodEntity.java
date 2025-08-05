package com.trainerapp.calorie_calculator.infrastructure.persistence.entity;

import com.trainerapp.calorie_calculator.domain.enums.FoodOriginType;
import com.trainerapp.calorie_calculator.domain.enums.NutritionalFunctionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "foods") // Especifica el nombre de la tabla
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID publicId;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private FoodOriginType foodOrigin;

    @ElementCollection(targetClass = NutritionalFunctionType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "foods_nutritional_functions", joinColumns = @JoinColumn(name = "food_id"))
    private List<NutritionalFunctionType> nutritionalFunctions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "food_id")
    private List<MeasurementUnitEntity> measurementUnits = new ArrayList<>(); // Inicializado

    @Embedded
    private NutritionalInfoEntity nutritionalInfo;

    @ElementCollection
    @CollectionTable(name = "micronutrient_content", joinColumns = @JoinColumn(name = "food_id"))
    private List<MicronutrientContentEntity> micronutrients = new ArrayList<>(); // Inicializado

    @ManyToMany
    @JoinTable(
    name = "foods_tags",  // Nombre de la tabla intermedia
    joinColumns = @JoinColumn(name = "food_id"),  // Columna de Recipe
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagEntity> tags = new ArrayList<>();
}