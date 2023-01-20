package com.example.demo123.service;

import com.example.demo123.exception.FeatureAlreadyExistsException;
import com.example.demo123.exception.FeatureNotFoundException;
import com.example.demo123.model.CreateFeatureDto;
import com.example.demo123.model.EnableFeatureDto;
import com.example.demo123.model.Feature;
import com.example.demo123.model.FeatureDto;
import com.example.demo123.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FeatureService {
    private final FeatureRepository featureRepository;
    private final UserService userService;


    public Feature create(final CreateFeatureDto featureDto) {
        String featureName = featureDto.name();

        if (exists(featureName)) {
            throw new FeatureAlreadyExistsException(featureName);
        }

        final var feature = Feature.builder()
                .name(featureName)
                .enabled(false)
                .build();

        featureRepository.save(feature);

        return feature;
    }

    public List<FeatureDto> getAllEnabled() {
        return featureRepository.findAllByEnabledIsTrue()
                .stream()
                .map(f -> FeatureDto.builder()
                        .name(f.getName())
                        .enabled(f.isEnabled())
                        .build())
                .collect(toList());
    }

    @Transactional
    public void enable(final EnableFeatureDto enableFeature) {
        final var featureName = enableFeature.featureName();
        final var featureOptional = featureRepository.findByName(featureName);

        if (featureOptional.isEmpty()) {
            throw new FeatureNotFoundException(featureName);
        }

        final var feature = featureOptional.get();

        feature.enable();

        featureRepository.save(feature);

        userService.addUserFeature(feature, enableFeature.userName());
    }

    private boolean exists(final String featureName) {
        return featureRepository.existsByName(featureName);
    }
}
