package com.empresa.featureflag_api.Repository;

import com.empresa.featureflag_api.Model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, UUID> {

    boolean existsByName(String name);
}
