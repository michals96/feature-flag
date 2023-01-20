package com.example.demo123.api;

import com.example.demo123.model.FeatureDto;
import com.example.demo123.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

    @GetMapping("/features")
    List<FeatureDto> getAllEnabled(Authentication authentication) throws Exception {
        return userService.getAllUserEnabledFeatures(getUserName(authentication));
    }

    private String getUserName(final Authentication authentication) throws Exception {
        final var principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            throw new Exception("Authentication Error!");
        }
    }
}
