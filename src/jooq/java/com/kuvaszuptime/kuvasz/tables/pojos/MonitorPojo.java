/*
 * This file is generated by jOOQ.
 */
package com.kuvaszuptime.kuvasz.tables.pojos;


import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "monitor", uniqueConstraints = {
    @UniqueConstraint(name = "monitor_pkey", columnNames = {"id"}),
    @UniqueConstraint(name = "unique_monitor_name", columnNames = {"name"})
})
public class MonitorPojo implements Serializable {

    private static final long serialVersionUID = -1828922669;

    private Integer        id;
    private String         name;
    private String         url;
    private Integer        uptimeCheckInterval;
    private Boolean        enabled;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Boolean        sslCheckEnabled;
    private String         pagerdutyIntegrationKey;

    public MonitorPojo() {}

    public MonitorPojo(MonitorPojo value) {
        this.id = value.id;
        this.name = value.name;
        this.url = value.url;
        this.uptimeCheckInterval = value.uptimeCheckInterval;
        this.enabled = value.enabled;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
        this.sslCheckEnabled = value.sslCheckEnabled;
        this.pagerdutyIntegrationKey = value.pagerdutyIntegrationKey;
    }

    public MonitorPojo(
        Integer        id,
        String         name,
        String         url,
        Integer        uptimeCheckInterval,
        Boolean        enabled,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Boolean        sslCheckEnabled,
        String         pagerdutyIntegrationKey
    ) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.uptimeCheckInterval = uptimeCheckInterval;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sslCheckEnabled = sslCheckEnabled;
        this.pagerdutyIntegrationKey = pagerdutyIntegrationKey;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 32)
    public Integer getId() {
        return this.id;
    }

    public MonitorPojo setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "name", nullable = false, length = 255)
    @NotNull
    @Size(max = 255)
    public String getName() {
        return this.name;
    }

    public MonitorPojo setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "url", nullable = false)
    @NotNull
    public String getUrl() {
        return this.url;
    }

    public MonitorPojo setUrl(String url) {
        this.url = url;
        return this;
    }

    @Column(name = "uptime_check_interval", nullable = false, precision = 32)
    @NotNull
    public Integer getUptimeCheckInterval() {
        return this.uptimeCheckInterval;
    }

    public MonitorPojo setUptimeCheckInterval(Integer uptimeCheckInterval) {
        this.uptimeCheckInterval = uptimeCheckInterval;
        return this;
    }

    @Column(name = "enabled", nullable = false)
    public Boolean getEnabled() {
        return this.enabled;
    }

    public MonitorPojo setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Column(name = "created_at", nullable = false)
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    public MonitorPojo setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Column(name = "updated_at")
    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public MonitorPojo setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Column(name = "ssl_check_enabled", nullable = false)
    public Boolean getSslCheckEnabled() {
        return this.sslCheckEnabled;
    }

    public MonitorPojo setSslCheckEnabled(Boolean sslCheckEnabled) {
        this.sslCheckEnabled = sslCheckEnabled;
        return this;
    }

    @Column(name = "pagerduty_integration_key")
    public String getPagerdutyIntegrationKey() {
        return this.pagerdutyIntegrationKey;
    }

    public MonitorPojo setPagerdutyIntegrationKey(String pagerdutyIntegrationKey) {
        this.pagerdutyIntegrationKey = pagerdutyIntegrationKey;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MonitorPojo other = (MonitorPojo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        }
        else if (!url.equals(other.url))
            return false;
        if (uptimeCheckInterval == null) {
            if (other.uptimeCheckInterval != null)
                return false;
        }
        else if (!uptimeCheckInterval.equals(other.uptimeCheckInterval))
            return false;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        }
        else if (!enabled.equals(other.enabled))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!createdAt.equals(other.createdAt))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!updatedAt.equals(other.updatedAt))
            return false;
        if (sslCheckEnabled == null) {
            if (other.sslCheckEnabled != null)
                return false;
        }
        else if (!sslCheckEnabled.equals(other.sslCheckEnabled))
            return false;
        if (pagerdutyIntegrationKey == null) {
            if (other.pagerdutyIntegrationKey != null)
                return false;
        }
        else if (!pagerdutyIntegrationKey.equals(other.pagerdutyIntegrationKey))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
        result = prime * result + ((this.uptimeCheckInterval == null) ? 0 : this.uptimeCheckInterval.hashCode());
        result = prime * result + ((this.enabled == null) ? 0 : this.enabled.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.sslCheckEnabled == null) ? 0 : this.sslCheckEnabled.hashCode());
        result = prime * result + ((this.pagerdutyIntegrationKey == null) ? 0 : this.pagerdutyIntegrationKey.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MonitorPojo (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(url);
        sb.append(", ").append(uptimeCheckInterval);
        sb.append(", ").append(enabled);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(sslCheckEnabled);
        sb.append(", ").append(pagerdutyIntegrationKey);

        sb.append(")");
        return sb.toString();
    }
}
