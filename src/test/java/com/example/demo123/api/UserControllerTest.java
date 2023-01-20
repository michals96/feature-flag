package com.example.demo123.api;

import com.example.demo123.model.Feature;
import com.example.demo123.model.FeatureDto;
import com.example.demo123.service.FeatureService;
import com.example.demo123.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Import({UserController.class})
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@WebMvcTest(UserControllerTest.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void shouldReturnOkStatusOnCreateFeature() throws Exception {
        final var feature = FeatureDto.builder().name("1feature").enabled(true).build();
        when(userService.getAllUserEnabledFeatures(any())).thenReturn(List.of(feature));

        this.mockMvc.perform(
                        get("/user/features"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn401OnMissingAuth() throws Exception {
        final var feature = FeatureDto.builder().name("1feature").enabled(true).build();
        when(userService.getAllUserEnabledFeatures(any())).thenReturn(List.of(feature));

        this.mockMvc.perform(
                        get("/user/features"))
                .andExpect(status().isUnauthorized());
    }
}