package com.example.demo123.service;

import com.example.demo123.exception.UserNotFoundException;
import com.example.demo123.model.Feature;
import com.example.demo123.model.FeatureDto;
import com.example.demo123.model.User;
import com.example.demo123.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(final String username) {
        final var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UserNotFoundException(username);
        }

        return user.get();
    }

    public List<FeatureDto> getAllUserEnabledFeatures(final String username) {
        final var user = userRepository.getByUsername(username);

        final var features = user.getFeatures();

        return features
                .stream()
                .map(f -> FeatureDto.builder()
                        .name(f.getName())
                        .enabled(f.isEnabled())
                        .build())
                .collect(toList());
    }

    public void addUserFeature(final Feature feature, final String username) {
        final var user = getUser(username);
        user.addFeature(feature);
    }
}
