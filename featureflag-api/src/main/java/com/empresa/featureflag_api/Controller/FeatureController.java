package com.empresa.featureflag_api.Controller;

import com.empresa.featureflag_api.Dto.feature.FeatureAddResponse;
import com.empresa.featureflag_api.Dto.feature.FeatureRequest;
import com.empresa.featureflag_api.Service.FeatureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class FeatureController {
    private FeatureService featureService;
    public FeatureController(FeatureService featureService){
        this.featureService=featureService;
    }

    @PostMapping("/features")
    public ResponseEntity<?> add(@Valid @RequestBody FeatureRequest featureRequest) {
        try {
            FeatureAddResponse response = featureService.add(featureRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("name", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }
}
