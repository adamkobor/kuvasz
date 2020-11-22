/*
 * This file is generated by jOOQ.
 */
package com.kuvaszuptime.kuvasz.tables.pojos;


import com.kuvaszuptime.kuvasz.enums.UptimeStatus;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(
    name = "uptime_event",
    uniqueConstraints = {
        @UniqueConstraint(name = "uptime_event_pkey", columnNames = { "id" }),
        @UniqueConstraint(name = "uptime_event_key", columnNames = { "monitor_id", "status", "ended_at" })
    },
    indexes = {
        @Index(name = "uptime_event_ended_at_idx", columnList = "ended_at ASC"),
        @Index(name = "uptime_event_monitor_idx", columnList = "monitor_id ASC")
    }
)
public class UptimeEventPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer        id;
    private Integer        monitorId;
    private UptimeStatus   status;
    private String         error;
    private OffsetDateTime startedAt;
    private OffsetDateTime endedAt;
    private OffsetDateTime updatedAt;

    public UptimeEventPojo() {}

    public UptimeEventPojo(UptimeEventPojo value) {
        this.id = value.id;
        this.monitorId = value.monitorId;
        this.status = value.status;
        this.error = value.error;
        this.startedAt = value.startedAt;
        this.endedAt = value.endedAt;
        this.updatedAt = value.updatedAt;
    }

    public UptimeEventPojo(
        Integer        id,
        Integer        monitorId,
        UptimeStatus   status,
        String         error,
        OffsetDateTime startedAt,
        OffsetDateTime endedAt,
        OffsetDateTime updatedAt
    ) {
        this.id = id;
        this.monitorId = monitorId;
        this.status = status;
        this.error = error;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>uptime_event.id</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, precision = 32)
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>uptime_event.id</code>.
     */
    public UptimeEventPojo setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>uptime_event.monitor_id</code>.
     */
    @Column(name = "monitor_id", nullable = false, precision = 32)
    @NotNull
    public Integer getMonitorId() {
        return this.monitorId;
    }

    /**
     * Setter for <code>uptime_event.monitor_id</code>.
     */
    public UptimeEventPojo setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
        return this;
    }

    /**
     * Getter for <code>uptime_event.status</code>. Status of the event
     */
    @Column(name = "status", nullable = false)
    @NotNull
    public UptimeStatus getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>uptime_event.status</code>. Status of the event
     */
    public UptimeEventPojo setStatus(UptimeStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Getter for <code>uptime_event.error</code>.
     */
    @Column(name = "error")
    public String getError() {
        return this.error;
    }

    /**
     * Setter for <code>uptime_event.error</code>.
     */
    public UptimeEventPojo setError(String error) {
        this.error = error;
        return this;
    }

    /**
     * Getter for <code>uptime_event.started_at</code>. The current event started at
     */
    @Column(name = "started_at", nullable = false, precision = 6)
    public OffsetDateTime getStartedAt() {
        return this.startedAt;
    }

    /**
     * Setter for <code>uptime_event.started_at</code>. The current event started at
     */
    public UptimeEventPojo setStartedAt(OffsetDateTime startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    /**
     * Getter for <code>uptime_event.ended_at</code>. The current event ended at
     */
    @Column(name = "ended_at", precision = 6)
    public OffsetDateTime getEndedAt() {
        return this.endedAt;
    }

    /**
     * Setter for <code>uptime_event.ended_at</code>. The current event ended at
     */
    public UptimeEventPojo setEndedAt(OffsetDateTime endedAt) {
        this.endedAt = endedAt;
        return this;
    }

    /**
     * Getter for <code>uptime_event.updated_at</code>.
     */
    @Column(name = "updated_at", nullable = false, precision = 6)
    @NotNull
    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>uptime_event.updated_at</code>.
     */
    public UptimeEventPojo setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
        final UptimeEventPojo other = (UptimeEventPojo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (monitorId == null) {
            if (other.monitorId != null)
                return false;
        }
        else if (!monitorId.equals(other.monitorId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (error == null) {
            if (other.error != null)
                return false;
        }
        else if (!error.equals(other.error))
            return false;
        if (startedAt == null) {
            if (other.startedAt != null)
                return false;
        }
        else if (!startedAt.equals(other.startedAt))
            return false;
        if (endedAt == null) {
            if (other.endedAt != null)
                return false;
        }
        else if (!endedAt.equals(other.endedAt))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.monitorId == null) ? 0 : this.monitorId.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.error == null) ? 0 : this.error.hashCode());
        result = prime * result + ((this.startedAt == null) ? 0 : this.startedAt.hashCode());
        result = prime * result + ((this.endedAt == null) ? 0 : this.endedAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UptimeEventPojo (");

        sb.append(id);
        sb.append(", ").append(monitorId);
        sb.append(", ").append(status);
        sb.append(", ").append(error);
        sb.append(", ").append(startedAt);
        sb.append(", ").append(endedAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
