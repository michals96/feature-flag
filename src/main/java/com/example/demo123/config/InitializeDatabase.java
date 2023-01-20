package com.example.demo123.config;

import com.example.demo123.model.Feature;
import com.example.demo123.model.User;
import com.example.demo123.repository.FeatureRepository;
import com.example.demo123.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@RequiredArgsConstructor
class InitializeDatabase {

    private final UserRepository userRepository;
    private final FeatureRepository featureRepository;

    @PostConstruct
    private void init() {
        final var adminFeature = Feature.builder()
                .name("admin_feature")
                .enabled(true)
                .build();

        final var userFeature = Feature.builder()
                .name("user_feature")
                .enabled(true)
                .build();

        final var randomFeature = Feature.builder()
                .name("random_feature")
                .enabled(false)
                .build();

        featureRepository.save(adminFeature);
        featureRepository.save(randomFeature);
        featureRepository.save(userFeature);

        final var user = User.builder()
                .username("user")
                .features(Set.of(userFeature))
                .build();

        final var admin = User.builder()
                .username("admin")
                .features(Set.of(userFeature, adminFeature))
                .build();

        userRepository.save(user);
        userRepository.save(admin);
    }
}
