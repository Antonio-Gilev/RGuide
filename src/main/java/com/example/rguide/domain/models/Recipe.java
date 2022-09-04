package com.example.rguide.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user;

    private Integer likes;

    private String ingredients;

    @Column(columnDefinition="text")
    private String image;

    @OneToMany(mappedBy = "recipe")
    private List<Step> steps;

    public Recipe() {
    }

    public Recipe(String name, User user, String ingredients) {
        this.name = name;
        this.user = user;
        this.ingredients = ingredients;
    }

    public Recipe(String name, User user, String ingredients, String image) {
        this.name = name;
        this.user = user;
        this.ingredients = ingredients;
        this.image = image;
    }
}
