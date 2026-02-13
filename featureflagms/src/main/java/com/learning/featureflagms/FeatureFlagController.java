package com.learning.featureflagms;

import com.learning.featureflagms.Entity.FeatureFlag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/flags")
public class FeatureFlagController {

    private final FeatureFlagService service;

    public FeatureFlagController(FeatureFlagService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Boolean> getAllFlags() {
        return service.getAllFlags();
    }

    @PostMapping
    public FeatureFlag createFlag(@RequestBody FeatureFlag flag) {
        return service.createFeatureFlag(flag);
    }

    @PutMapping("/{key}")
    public FeatureFlag updateFlag(
            @PathVariable String key,
            @RequestParam boolean enabled
    ) {
        return service.updateFeatureFlag(key, enabled);
    }
}
