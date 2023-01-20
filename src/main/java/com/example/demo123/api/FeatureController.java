package com.example.demo123.api;

import com.example.demo123.model.CreateFeatureDto;
import com.example.demo123.model.EnableFeatureDto;
import com.example.demo123.model.FeatureDto;
import com.example.demo123.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
@RequiredArgsConstructor
class FeatureController {
    private final FeatureService featureService;

    @PostMapping("/create")
    ResponseEntity<String> create(@RequestBody CreateFeatureDto featureDto) {
        featureService.create(featureDto);
        return ResponseEntity.ok("Feature created!");
    }

    @GetMapping("/enabled")
    List<FeatureDto> getAll() {
        return featureService.getAllEnabled();
    }

    @PatchMapping("/enable")
    ResponseEntity<String> enable(@RequestBody EnableFeatureDto feature) {
        featureService.enable(feature);
        return ResponseEntity.ok("Feature enabled!");
    }
}
