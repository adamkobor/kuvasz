/*
 * This file is generated by jOOQ.
 */
package com.kuvaszuptime.kuvasz.tables;


import com.kuvaszuptime.kuvasz.DefaultSchema;
import com.kuvaszuptime.kuvasz.Indexes;
import com.kuvaszuptime.kuvasz.Keys;
import com.kuvaszuptime.kuvasz.tables.records.PagerdutyIncidentRecord;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PagerdutyIncident extends TableImpl<PagerdutyIncidentRecord> {

    private static final long serialVersionUID = 1446234332;

    /**
     * The reference instance of <code>pagerduty_incident</code>
     */
    public static final PagerdutyIncident PAGERDUTY_INCIDENT = new PagerdutyIncident();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PagerdutyIncidentRecord> getRecordType() {
        return PagerdutyIncidentRecord.class;
    }

    /**
     * The column <code>pagerduty_incident.id</code>.
     */
    public final TableField<PagerdutyIncidentRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('kuvasz.pagerduty_incident_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>pagerduty_incident.uptime_event_id</code>.
     */
    public final TableField<PagerdutyIncidentRecord, Integer> UPTIME_EVENT_ID = createField(DSL.name("uptime_event_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>pagerduty_incident.ssl_event_id</code>.
     */
    public final TableField<PagerdutyIncidentRecord, Integer> SSL_EVENT_ID = createField(DSL.name("ssl_event_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>pagerduty_incident.deduplication_key</code>.
     */
    public final TableField<PagerdutyIncidentRecord, String> DEDUPLICATION_KEY = createField(DSL.name("deduplication_key"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>pagerduty_incident.started_at</code>.
     */
    public final TableField<PagerdutyIncidentRecord, OffsetDateTime> STARTED_AT = createField(DSL.name("started_at"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * The column <code>pagerduty_incident.ended_at</code>.
     */
    public final TableField<PagerdutyIncidentRecord, OffsetDateTime> ENDED_AT = createField(DSL.name("ended_at"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * Create a <code>pagerduty_incident</code> table reference
     */
    public PagerdutyIncident() {
        this(DSL.name("pagerduty_incident"), null);
    }

    /**
     * Create an aliased <code>pagerduty_incident</code> table reference
     */
    public PagerdutyIncident(String alias) {
        this(DSL.name(alias), PAGERDUTY_INCIDENT);
    }

    /**
     * Create an aliased <code>pagerduty_incident</code> table reference
     */
    public PagerdutyIncident(Name alias) {
        this(alias, PAGERDUTY_INCIDENT);
    }

    private PagerdutyIncident(Name alias, Table<PagerdutyIncidentRecord> aliased) {
        this(alias, aliased, null);
    }

    private PagerdutyIncident(Name alias, Table<PagerdutyIncidentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> PagerdutyIncident(Table<O> child, ForeignKey<O, PagerdutyIncidentRecord> key) {
        super(child, key, PAGERDUTY_INCIDENT);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PAGERDUTY_INCIDENT_DEDUP_KEY_IDX, Indexes.PAGERDUTY_INCIDENT_ENDED_AT_IDX, Indexes.PAGERDUTY_INCIDENT_SSL_EVENT_IDX, Indexes.PAGERDUTY_INCIDENT_UPTIME_EVENT_IDX);
    }

    @Override
    public Identity<PagerdutyIncidentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PAGERDUTY_INCIDENT;
    }

    @Override
    public UniqueKey<PagerdutyIncidentRecord> getPrimaryKey() {
        return Keys.PAGERDUTY_INCIDENT_PKEY;
    }

    @Override
    public List<UniqueKey<PagerdutyIncidentRecord>> getKeys() {
        return Arrays.<UniqueKey<PagerdutyIncidentRecord>>asList(Keys.PAGERDUTY_INCIDENT_PKEY);
    }

    @Override
    public List<ForeignKey<PagerdutyIncidentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PagerdutyIncidentRecord, ?>>asList(Keys.PAGERDUTY_INCIDENT__PAGERDUTY_INCIDENT_UPTIME_EVENT_ID_FKEY, Keys.PAGERDUTY_INCIDENT__PAGERDUTY_INCIDENT_SSL_EVENT_ID_FKEY);
    }

    public UptimeEvent uptimeEvent() {
        return new UptimeEvent(this, Keys.PAGERDUTY_INCIDENT__PAGERDUTY_INCIDENT_UPTIME_EVENT_ID_FKEY);
    }

    public SslEvent sslEvent() {
        return new SslEvent(this, Keys.PAGERDUTY_INCIDENT__PAGERDUTY_INCIDENT_SSL_EVENT_ID_FKEY);
    }

    @Override
    public PagerdutyIncident as(String alias) {
        return new PagerdutyIncident(DSL.name(alias), this);
    }

    @Override
    public PagerdutyIncident as(Name alias) {
        return new PagerdutyIncident(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PagerdutyIncident rename(String name) {
        return new PagerdutyIncident(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PagerdutyIncident rename(Name name) {
        return new PagerdutyIncident(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, Integer, String, OffsetDateTime, OffsetDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
