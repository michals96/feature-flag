package com.example.demo123.api;

import com.example.demo123.model.Feature;
import com.example.demo123.service.FeatureService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Import({FeatureController.class})
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@WebMvcTest(FeatureControllerTest.class)
class FeatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeatureService featureService;

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void shouldReturnOkStatusOnCreateFeature() throws Exception {
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        when(featureService.create(any())).thenReturn(feature);

        this.mockMvc.perform(
                        post("/feature/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dummyValidRequest()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = "ADMIN")
    void shouldReturn4xxStatusOnMissingBody() throws Exception {
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        when(featureService.create(any())).thenReturn(feature);

        this.mockMvc.perform(
                        post("/feature/create")
                                .content(dummyValidRequest()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    void shouldReturn403OnMissingAccess() throws Exception {
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        when(featureService.create(any())).thenReturn(feature);

        this.mockMvc.perform(
                        post("/feature/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dummyValidRequest()))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturn401OnMissingAuth() throws Exception {
        final var feature = Feature.builder().name("1feature").enabled(false).build();
        when(featureService.create(any())).thenReturn(feature);

        this.mockMvc.perform(
                        post("/feature/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dummyValidRequest()))
                .andExpect(status().isUnauthorized());
    }

    private String dummyValidRequest() {
        return "{\n" +
                "    \"name\": \"feature random2\"\n" +
                "}";
    }
}