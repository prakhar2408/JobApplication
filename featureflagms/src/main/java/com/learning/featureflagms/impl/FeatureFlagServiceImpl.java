package com.learning.featureflagms.impl;

import com.learning.featureflagms.Entity.FeatureFlag;
import com.learning.featureflagms.FeatureFlagRepository;
import com.learning.featureflagms.FeatureFlagService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FeatureFlagServiceImpl implements FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagServiceImpl(FeatureFlagRepository repository) {
        this.repository = repository;
    }

    @Override
    public FeatureFlag createFeatureFlag(FeatureFlag flag) {
        return repository.save(flag);
    }

    @Override
    public Map<String, Boolean> getAllFlags() {
        return repository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        FeatureFlag::getFlagKey,
                        FeatureFlag::isEnabled
                ));
    }

    @Override
    public FeatureFlag updateFeatureFlag(String key, boolean enabled) {
        FeatureFlag flag = repository.findByFlagKey(key)
                .orElseThrow(() -> new RuntimeException("Flag not found"));
        flag.setEnabled(enabled);
        return repository.save(flag);
    }

    @Override
    public void deleteFeatureFlagByKey(String key) {
        System.out.println("Delete Key");;
    }
}
