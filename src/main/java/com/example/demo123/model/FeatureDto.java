package com.example.demo123.model;

import lombok.Builder;

@Builder
public record FeatureDto(String name, boolean enabled) {
}
