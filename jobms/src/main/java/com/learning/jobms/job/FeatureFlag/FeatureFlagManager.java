package com.learning.jobms.job.FeatureFlag;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FeatureFlagManager {

    private final Map<String, Boolean> flags = new ConcurrentHashMap<>();

    public boolean isEnabled(String key) {
        return flags.getOrDefault(key, false);
    }

    public void update(Map<String, Boolean> newFlags) {
        if (newFlags == null) return;

        flags.clear();          // important
        flags.putAll(newFlags); // important
    }
}