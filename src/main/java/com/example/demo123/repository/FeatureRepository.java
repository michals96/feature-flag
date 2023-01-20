package com.example.demo123.repository;

import com.example.demo123.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Optional<Feature> findByName(String name);

    List<Feature> findAllByEnabledIsTrue();

    boolean existsByName(String name);
}
