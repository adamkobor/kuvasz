package com.kuvaszuptime.kuvasz.models.events

import arrow.core.Option
import arrow.core.getOrElse
import arrow.core.toOption
import com.kuvaszuptime.kuvasz.enums.SslStatus
import com.kuvaszuptime.kuvasz.enums.UptimeStatus
import com.kuvaszuptime.kuvasz.models.CertificateInfo
import com.kuvaszuptime.kuvasz.models.SSLValidationError
import com.kuvaszuptime.kuvasz.tables.pojos.MonitorPojo
import com.kuvaszuptime.kuvasz.tables.pojos.SslEventPojo
import com.kuvaszuptime.kuvasz.tables.pojos.UptimeEventPojo
import com.kuvaszuptime.kuvasz.util.diffToDuration
import com.kuvaszuptime.kuvasz.util.getCurrentTimestamp
import com.kuvaszuptime.kuvasz.util.toDurationString
import io.micronaut.http.HttpStatus
import java.net.URI
import kotlin.time.Duration

sealed class MonitorEvent {
    abstract val monitor: MonitorPojo

    abstract fun toStructuredMessage(): StructuredMessage

    val dispatchedAt = getCurrentTimestamp()
}

sealed class UptimeMonitorEvent : MonitorEvent() {
    abstract val previousEvent: Option<UptimeEventPojo>

    abstract val uptimeStatus: UptimeStatus

    fun statusNotEquals(previousEvent: UptimeEventPojo) = !statusEquals(previousEvent)

    fun getEndedEventDuration(): Option<Duration> =
        previousEvent.flatMap { previousEvent ->
            Option.fromNullable(
                if (statusNotEquals(previousEvent)) {
                    previousEvent.startedAt.diffToDuration(dispatchedAt)
                } else null
            )
        }

    fun runWhenStateChanges(toRun: (UptimeMonitorEvent) -> Unit) {
        return previousEvent.fold(
            { toRun(this) },
            { previousEvent ->
                if (statusNotEquals(previousEvent)) {
                    toRun(this)
                }
            }
        )
    }

    private fun statusEquals(previousEvent: UptimeEventPojo) = uptimeStatus == previousEvent.status
}

data class MonitorUpEvent(
    override val monitor: MonitorPojo,
    val status: HttpStatus,
    val latency: Int,
    override val previousEvent: Option<UptimeEventPojo>
) : UptimeMonitorEvent() {

    override val uptimeStatus = UptimeStatus.UP

    override fun toStructuredMessage() =
        StructuredMonitorUpMessage(
            summary = "Your monitor \"${monitor.name}\" (${monitor.url}) is UP (${status.code})",
            latency = "Latency: ${latency}ms",
            previousDownTime = getEndedEventDuration().toDurationString().map { "Was down for $it" }
        )
}

data class MonitorDownEvent(
    override val monitor: MonitorPojo,
    val status: HttpStatus?,
    val error: Throwable,
    override val previousEvent: Option<UptimeEventPojo>
) : UptimeMonitorEvent() {

    override val uptimeStatus = UptimeStatus.DOWN

    override fun toStructuredMessage() =
        StructuredMonitorDownMessage(
            summary = "Your monitor \"${monitor.name}\" (${monitor.url}) is DOWN" +
                    status.toOption().map { " (" + it.code + ")" }.getOrElse { "" },
            error = "Reason: ${error.message}",
            previousUpTime = getEndedEventDuration().toDurationString().map { "Was up for $it" }
        )
}

data class RedirectEvent(
    override val monitor: MonitorPojo,
    val redirectLocation: URI
) : MonitorEvent() {

    override fun toStructuredMessage() = StructuredRedirectMessage(
        summary = "Request to \"${monitor.name}\" (${monitor.url}) has been redirected"
    )
}

sealed class SSLMonitorEvent : MonitorEvent() {
    abstract val previousEvent: Option<SslEventPojo>

    abstract val sslStatus: SslStatus

    fun statusNotEquals(previousEvent: SslEventPojo) = !statusEquals(previousEvent)

    fun getEndedEventDuration(): Option<Duration> =
        previousEvent.flatMap { previousEvent ->
            Option.fromNullable(
                if (statusNotEquals(previousEvent)) {
                    previousEvent.startedAt.diffToDuration(dispatchedAt)
                } else null
            )
        }

    fun getPreviousStatusString(): String = previousEvent.map { it.status.name }.getOrElse { "" }

    fun runWhenStateChanges(toRun: (SSLMonitorEvent) -> Unit) {
        return previousEvent.fold(
            { toRun(this) },
            { previousEvent ->
                if (statusNotEquals(previousEvent)) {
                    toRun(this)
                }
            }
        )
    }

    private fun statusEquals(previousEvent: SslEventPojo) = sslStatus == previousEvent.status
}

data class SSLValidEvent(
    override val monitor: MonitorPojo,
    val certInfo: CertificateInfo,
    override val previousEvent: Option<SslEventPojo>
) : SSLMonitorEvent() {

    override val sslStatus = SslStatus.VALID

    override fun toStructuredMessage() =
        StructuredSSLValidMessage(
            summary = "Your site \"${monitor.name}\" (${monitor.url}) has a VALID certificate",
            previousInvalidEvent = getEndedEventDuration().toDurationString()
                .map { "Was ${getPreviousStatusString()} for $it" }
        )
}

data class SSLInvalidEvent(
    override val monitor: MonitorPojo,
    val error: SSLValidationError,
    override val previousEvent: Option<SslEventPojo>
) : SSLMonitorEvent() {

    override val sslStatus = SslStatus.INVALID

    override fun toStructuredMessage() =
        StructuredSSLInvalidMessage(
            summary = "Your site \"${monitor.name}\" (${monitor.url}) has an INVALID certificate",
            error = "Reason: ${error.message}",
            previousValidEvent = getEndedEventDuration().toDurationString()
                .map { "Was ${getPreviousStatusString()} for $it" }
        )
}

data class SSLWillExpireEvent(
    override val monitor: MonitorPojo,
    val certInfo: CertificateInfo,
    override val previousEvent: Option<SslEventPojo>
) : SSLMonitorEvent() {

    override val sslStatus = SslStatus.WILL_EXPIRE

    override fun toStructuredMessage() =
        StructuredSSLWillExpireMessage(
            summary = "Your SSL certificate for ${monitor.url} will expire soon",
            validUntil = "Expiry date: ${certInfo.validTo}"
        )
}