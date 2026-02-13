package com.learning.jobms.job.FeatureFlag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FeatureFlagRefresher {

    private final FeatureFlagManager manager;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${flag.service.url}")
    private String flagServiceUrl;

    public FeatureFlagRefresher(FeatureFlagManager manager) {
        this.manager = manager;
    }

    @Scheduled(fixedDelayString = "${flag.refresh-interval-ms}")
    public void refresh() {
        try {
            Map<String, Boolean> flags =
                    restTemplate.getForObject(flagServiceUrl, Map.class);

            if (flags != null) {
                manager.update(flags);
                System.out.println("Feature flags refreshed: " + flags);
            }
        } catch (Exception e) {
            System.err.println("Feature flag refresh failed: " + e.getMessage());
        }
    }
}