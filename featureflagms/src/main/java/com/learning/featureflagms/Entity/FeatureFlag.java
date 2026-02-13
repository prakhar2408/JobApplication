package com.learning.featureflagms.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feature_flags")
public class FeatureFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flag_key", unique = true, nullable = false)
    private String flagKey;

    private boolean enabled;
    private String description;

    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    void updateTimestamp() {
        lastUpdated = LocalDateTime.now();
    }

    public String getFlagKey() { return flagKey; }
    public boolean isEnabled() { return enabled; }

    public void setFlagKey(String flagKey) { this.flagKey = flagKey; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public void setDescription(String description) { this.description = description; }
}
