package com.empresa.featureflag_api.Dto.feature;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeatureAddResponse {
    private String name;
    private String description;
    private Boolean enabledByDefault;
}
