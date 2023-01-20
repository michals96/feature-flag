package com.example.demo123.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @ManyToMany
    @JoinTable(name = "user_features", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "feature_id", referencedColumnName = "id"
            ))
    private Set<Feature> features = new HashSet<>();

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }
}
