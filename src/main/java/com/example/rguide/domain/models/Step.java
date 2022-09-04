package com.example.rguide.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(columnDefinition="text")
    private String image;

    @ManyToOne
    private Recipe recipe;

    public Step(String description, String image, Recipe recipe) {
        this.description = description;
        this.image = image;
        this.recipe = recipe;
    }

    public Step() {
    }
}
