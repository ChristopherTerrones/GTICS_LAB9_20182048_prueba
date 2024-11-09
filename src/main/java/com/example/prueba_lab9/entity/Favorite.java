package com.example.prueba_lab9.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mealid", nullable = false)
    private Integer mealId;

    @Column(name = "mealname", nullable = false)
    private String mealName;

    @Column(name = "mealcategory")
    private String mealCategory;

    @Column(name = "mealimage")
    private String mealImage;
}
