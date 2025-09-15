package com.empresa.featureflag_api.Service;

import com.empresa.featureflag_api.Dto.feature.FeatureAddResponse;
import com.empresa.featureflag_api.Dto.feature.FeatureRequest;
import com.empresa.featureflag_api.Model.Feature;
import com.empresa.featureflag_api.Repository.FeatureRepository;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }


    public FeatureAddResponse add(FeatureRequest featureRequest){
        if (featureRepository.existsByName(featureRequest.getName())) {
            throw new IllegalArgumentException("El nombre ya está en uso");
        }
        Feature feature = new Feature();
        feature.setName(featureRequest.getName());
        feature.setDescription(featureRequest.getDescription());
        featureRepository.save(feature);
        return new FeatureAddResponse(feature.getName(),feature.getDescription(),feature.getEnabledByDefault());
    }
}
