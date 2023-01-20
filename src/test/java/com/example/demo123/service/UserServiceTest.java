package com.example.demo123.service;

import com.example.demo123.exception.UserNotFoundException;
import com.example.demo123.model.Feature;
import com.example.demo123.model.User;
import com.example.demo123.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    void shouldGetUser() {
        // given
        final var user = User.builder().username("user").build();

        // when
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        // then
        final var returnedUser = userService.getUser("user");
        assertEquals(user, returnedUser);
    }

    @Test
    void shouldThrowExceptionOnNonExistingUser() {
        // when
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // then
        assertThrows(UserNotFoundException.class, () -> userService.getUser("user"));
    }

    @Test
    void shouldGetAllUserEnabledFeatures() {
        // given
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        final var user = User.builder().username("user").features(Set.of(feature)).build();

        // when
        when(userRepository.getByUsername(any())).thenReturn(user);

        // then
        final var features = userService.getAllUserEnabledFeatures("user");

        assertEquals(features.size(), 1);
    }

    @Test
    void shouldAddUserFeature() {
        // given
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        final var user = User.builder().username("user").features(new HashSet<>()).build();

        // when
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        // then
        userService.addUserFeature(feature, "user");
        assertEquals(user.getFeatures().size(), 1);
    }

}