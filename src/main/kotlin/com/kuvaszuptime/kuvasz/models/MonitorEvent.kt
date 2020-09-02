package com.kuvaszuptime.kuvasz.models

import arrow.core.Option
import com.kuvaszuptime.kuvasz.tables.pojos.MonitorPojo
import com.kuvaszuptime.kuvasz.tables.pojos.SslEventPojo
import com.kuvaszuptime.kuvasz.tables.pojos.UptimeEventPojo
import com.kuvaszuptime.kuvasz.util.getCurrentTimestamp
import io.micronaut.http.HttpStatus
import java.net.URI

sealed class MonitorEvent {
    abstract val monitor: MonitorPojo
    val dispatchedAt = getCurrentTimestamp()
}

sealed class UptimeMonitorEvent : MonitorEvent() {
    abstract val previousEvent: Option<UptimeEventPojo>
}

data class MonitorUpEvent(
    override val monitor: MonitorPojo,
    val status: HttpStatus,
    val latency: Int,
    override val previousEvent: Option<UptimeEventPojo>
) : UptimeMonitorEvent()

data class MonitorDownEvent(
    override val monitor: MonitorPojo,
    val status: HttpStatus?,
    val error: Throwable,
    override val previousEvent: Option<UptimeEventPojo>
) : UptimeMonitorEvent()

data class RedirectEvent(
    override val monitor: MonitorPojo,
    val redirectLocation: URI
) : MonitorEvent()

sealed class SSLMonitorEvent : MonitorEvent() {
    abstract val previousEvent: Option<SslEventPojo>
}

data class SSLValidEvent(
    override val monitor: MonitorPojo,
    val certInfo: CertificateInfo,
    override val previousEvent: Option<SslEventPojo>
) : SSLMonitorEvent()

data class SSLInvalidEvent(
    override val monitor: MonitorPojo,
    val error: SSLValidationError,
    override val previousEvent: Option<SslEventPojo>
) : SSLMonitorEvent()

data class SSLWillExpireEvent(
    override val monitor: MonitorPojo,
    val certInfo: CertificateInfo,
    override val previousEvent: Option<SslEventPojo>
) : SSLMonitorEvent()
