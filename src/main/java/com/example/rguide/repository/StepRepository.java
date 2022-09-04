package com.example.rguide.repository;

import com.example.rguide.domain.models.Recipe;
import com.example.rguide.domain.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findAllByRecipe(Recipe recipe);
}
