/*
 * This file is generated by jOOQ.
 */
package com.kuvaszuptime.kuvasz;


import com.kuvaszuptime.kuvasz.tables.LatencyLog;
import com.kuvaszuptime.kuvasz.tables.SslEvent;
import com.kuvaszuptime.kuvasz.tables.UptimeEvent;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in the default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index LATENCY_LOG_LATENCY_IDX = Internal.createIndex(DSL.name("latency_log_latency_idx"), LatencyLog.LATENCY_LOG, new OrderField[] { LatencyLog.LATENCY_LOG.LATENCY }, false);
    public static final Index LATENCY_LOG_MONITOR_IDX = Internal.createIndex(DSL.name("latency_log_monitor_idx"), LatencyLog.LATENCY_LOG, new OrderField[] { LatencyLog.LATENCY_LOG.MONITOR_ID }, false);
    public static final Index SSL_EVENT_ENDED_AT_IDX = Internal.createIndex(DSL.name("ssl_event_ended_at_idx"), SslEvent.SSL_EVENT, new OrderField[] { SslEvent.SSL_EVENT.ENDED_AT }, false);
    public static final Index SSL_EVENT_MONITOR_IDX = Internal.createIndex(DSL.name("ssl_event_monitor_idx"), SslEvent.SSL_EVENT, new OrderField[] { SslEvent.SSL_EVENT.MONITOR_ID }, false);
    public static final Index UPTIME_EVENT_ENDED_AT_IDX = Internal.createIndex(DSL.name("uptime_event_ended_at_idx"), UptimeEvent.UPTIME_EVENT, new OrderField[] { UptimeEvent.UPTIME_EVENT.ENDED_AT }, false);
    public static final Index UPTIME_EVENT_MONITOR_IDX = Internal.createIndex(DSL.name("uptime_event_monitor_idx"), UptimeEvent.UPTIME_EVENT, new OrderField[] { UptimeEvent.UPTIME_EVENT.MONITOR_ID }, false);
}
