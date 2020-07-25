/*
 * This file is generated by jOOQ.
 */
package com.akobor.kuvasz.tables.daos;


import com.akobor.kuvasz.tables.Monitor;
import com.akobor.kuvasz.tables.pojos.MonitorPojo;
import com.akobor.kuvasz.tables.records.MonitorRecord;

import java.time.OffsetDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MonitorDao extends DAOImpl<MonitorRecord, MonitorPojo, Integer> {

    /**
     * Create a new MonitorDao without any configuration
     */
    public MonitorDao() {
        super(Monitor.MONITOR, MonitorPojo.class);
    }

    /**
     * Create a new MonitorDao with an attached configuration
     */
    public MonitorDao(Configuration configuration) {
        super(Monitor.MONITOR, MonitorPojo.class, configuration);
    }

    @Override
    public Integer getId(MonitorPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Monitor.MONITOR.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<MonitorPojo> fetchById(Integer... values) {
        return fetch(Monitor.MONITOR.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public MonitorPojo fetchOneById(Integer value) {
        return fetchOne(Monitor.MONITOR.ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Monitor.MONITOR.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<MonitorPojo> fetchByName(String... values) {
        return fetch(Monitor.MONITOR.NAME, values);
    }

    /**
     * Fetch records that have <code>url BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfUrl(String lowerInclusive, String upperInclusive) {
        return fetchRange(Monitor.MONITOR.URL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>url IN (values)</code>
     */
    public List<MonitorPojo> fetchByUrl(String... values) {
        return fetch(Monitor.MONITOR.URL, values);
    }

    /**
     * Fetch records that have <code>uptime_check_interval BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfUptimeCheckInterval(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Monitor.MONITOR.UPTIME_CHECK_INTERVAL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>uptime_check_interval IN (values)</code>
     */
    public List<MonitorPojo> fetchByUptimeCheckInterval(Integer... values) {
        return fetch(Monitor.MONITOR.UPTIME_CHECK_INTERVAL, values);
    }

    /**
     * Fetch records that have <code>enabled BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfEnabled(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Monitor.MONITOR.ENABLED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>enabled IN (values)</code>
     */
    public List<MonitorPojo> fetchByEnabled(Boolean... values) {
        return fetch(Monitor.MONITOR.ENABLED, values);
    }

    /**
     * Fetch records that have <code>created_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfCreatedAt(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(Monitor.MONITOR.CREATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_at IN (values)</code>
     */
    public List<MonitorPojo> fetchByCreatedAt(OffsetDateTime... values) {
        return fetch(Monitor.MONITOR.CREATED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<MonitorPojo> fetchRangeOfUpdatedAt(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(Monitor.MONITOR.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<MonitorPojo> fetchByUpdatedAt(OffsetDateTime... values) {
        return fetch(Monitor.MONITOR.UPDATED_AT, values);
    }
}
