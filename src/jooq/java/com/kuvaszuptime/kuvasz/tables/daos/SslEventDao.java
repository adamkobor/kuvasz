/*
 * This file is generated by jOOQ.
 */
package com.kuvaszuptime.kuvasz.tables.daos;


import com.kuvaszuptime.kuvasz.enums.SslStatus;
import com.kuvaszuptime.kuvasz.tables.SslEvent;
import com.kuvaszuptime.kuvasz.tables.pojos.SslEventPojo;
import com.kuvaszuptime.kuvasz.tables.records.SslEventRecord;

import java.time.OffsetDateTime;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SslEventDao extends DAOImpl<SslEventRecord, SslEventPojo, Integer> {

    /**
     * Create a new SslEventDao without any configuration
     */
    public SslEventDao() {
        super(SslEvent.SSL_EVENT, SslEventPojo.class);
    }

    /**
     * Create a new SslEventDao with an attached configuration
     */
    public SslEventDao(Configuration configuration) {
        super(SslEvent.SSL_EVENT, SslEventPojo.class, configuration);
    }

    @Override
    public Integer getId(SslEventPojo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<SslEventPojo> fetchById(Integer... values) {
        return fetch(SslEvent.SSL_EVENT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public SslEventPojo fetchOneById(Integer value) {
        return fetchOne(SslEvent.SSL_EVENT.ID, value);
    }

    /**
     * Fetch records that have <code>monitor_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfMonitorId(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.MONITOR_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>monitor_id IN (values)</code>
     */
    public List<SslEventPojo> fetchByMonitorId(Integer... values) {
        return fetch(SslEvent.SSL_EVENT.MONITOR_ID, values);
    }

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfStatus(SslStatus lowerInclusive, SslStatus upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.STATUS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<SslEventPojo> fetchByStatus(SslStatus... values) {
        return fetch(SslEvent.SSL_EVENT.STATUS, values);
    }

    /**
     * Fetch records that have <code>error BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfError(String lowerInclusive, String upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.ERROR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>error IN (values)</code>
     */
    public List<SslEventPojo> fetchByError(String... values) {
        return fetch(SslEvent.SSL_EVENT.ERROR, values);
    }

    /**
     * Fetch records that have <code>started_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfStartedAt(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.STARTED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>started_at IN (values)</code>
     */
    public List<SslEventPojo> fetchByStartedAt(OffsetDateTime... values) {
        return fetch(SslEvent.SSL_EVENT.STARTED_AT, values);
    }

    /**
     * Fetch records that have <code>ended_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfEndedAt(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.ENDED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ended_at IN (values)</code>
     */
    public List<SslEventPojo> fetchByEndedAt(OffsetDateTime... values) {
        return fetch(SslEvent.SSL_EVENT.ENDED_AT, values);
    }

    /**
     * Fetch records that have <code>updated_at BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<SslEventPojo> fetchRangeOfUpdatedAt(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(SslEvent.SSL_EVENT.UPDATED_AT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_at IN (values)</code>
     */
    public List<SslEventPojo> fetchByUpdatedAt(OffsetDateTime... values) {
        return fetch(SslEvent.SSL_EVENT.UPDATED_AT, values);
    }
}