package com.learning.featureflagms;

import com.learning.featureflagms.Entity.FeatureFlag;

import java.util.Map;

public interface FeatureFlagService {

    FeatureFlag createFeatureFlag(FeatureFlag featureFlag);
    Map<String, Boolean> getAllFlags();
    FeatureFlag updateFeatureFlag(String key, boolean enabled);
    void deleteFeatureFlagByKey(String key);
}