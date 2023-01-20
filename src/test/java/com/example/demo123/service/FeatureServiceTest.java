package com.example.demo123.service;

import com.example.demo123.exception.FeatureAlreadyExistsException;
import com.example.demo123.exception.FeatureNotFoundException;
import com.example.demo123.model.CreateFeatureDto;
import com.example.demo123.model.EnableFeatureDto;
import com.example.demo123.model.Feature;
import com.example.demo123.repository.FeatureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FeatureServiceTest {
    @Mock
    UserService userService;
    @Mock
    FeatureRepository featureRepository;
    @InjectMocks
    FeatureService featureService;

    @Test
    void shouldCreateFeature() {
        // given
        final var createFeatureDto = CreateFeatureDto.builder().name("feature").build();

        // then
        final var feature = featureService.create(createFeatureDto);

        // assert
        assertEquals(feature.getName(), createFeatureDto.name());
    }

    @Test
    void shouldThrowExceptionOnAlreadyExistingFeature() {
        // given
        final var createFeatureDto = CreateFeatureDto.builder().name("feature").build();

        // when
        when(featureRepository.existsByName(any())).thenReturn(true);

        // assert
        assertThrows(FeatureAlreadyExistsException.class, () -> featureService.create(createFeatureDto));
    }

    @Test
    void featureShouldGetEnabled() {
        // given
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        final var enableFeature = EnableFeatureDto.builder().featureName("1feature").userName("user").build();

        // when
        when(featureRepository.findByName(any())).thenReturn(Optional.of(feature));
        featureService.enable(enableFeature);

        // then
        verify(featureRepository).save(feature);
        verify(userService).addUserFeature(feature, "user");
    }

    @Test
    void featureShouldNotGetEnabledOnIncorrectFeatureName() {
        // given
        final var enableFeature = EnableFeatureDto.builder().featureName("1feature").userName("user").build();

        // when
        when(featureRepository.findByName(any())).thenReturn(Optional.empty());

        // then
        assertThrows(FeatureNotFoundException.class, () -> featureService.enable(enableFeature));
    }
}