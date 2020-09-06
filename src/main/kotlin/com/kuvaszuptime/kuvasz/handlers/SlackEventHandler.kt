package com.kuvaszuptime.kuvasz.handlers

import com.kuvaszuptime.kuvasz.models.MonitorDownEvent
import com.kuvaszuptime.kuvasz.models.MonitorUpEvent
import com.kuvaszuptime.kuvasz.models.SSLInvalidEvent
import com.kuvaszuptime.kuvasz.models.SSLMonitorEvent
import com.kuvaszuptime.kuvasz.models.SSLValidEvent
import com.kuvaszuptime.kuvasz.models.SSLWillExpireEvent
import com.kuvaszuptime.kuvasz.models.SlackWebhookMessage
import com.kuvaszuptime.kuvasz.models.UptimeMonitorEvent
import com.kuvaszuptime.kuvasz.services.EventDispatcher
import com.kuvaszuptime.kuvasz.services.SlackWebhookService
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.reactivex.Flowable
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Context
@Requires(property = "handler-config.slack-event-handler.enabled", value = "true")
class SlackEventHandler @Inject constructor(
    private val slackWebhookService: SlackWebhookService,
    private val eventDispatcher: EventDispatcher
) {
    companion object {
        private val logger = LoggerFactory.getLogger(SlackEventHandler::class.java)
    }

    init {
        subscribeToEvents()
    }

    @ExecuteOn(TaskExecutors.IO)
    private fun subscribeToEvents() {
        eventDispatcher.subscribeToMonitorUpEvents { event ->
            logger.debug("A MonitorUpEvent has been received for monitor with ID: ${event.monitor.id}")
            event.runWhenStateChanges { slackWebhookService.sendMessage(it.toSlackMessage()).handleResponse() }
        }
        eventDispatcher.subscribeToMonitorDownEvents { event ->
            logger.debug("A MonitorDownEvent has been received for monitor with ID: ${event.monitor.id}")
            event.runWhenStateChanges { slackWebhookService.sendMessage(it.toSlackMessage()).handleResponse() }
        }
        eventDispatcher.subscribeToSSLValidEvents { event ->
            logger.debug("An SSLValidEvent has been received for monitor with ID: ${event.monitor.id}")
            event.runWhenStateChanges { slackWebhookService.sendMessage(it.toSlackMessage()).handleResponse() }
        }
        eventDispatcher.subscribeToSSLInvalidEvents { event ->
            logger.debug("An SSLInvalidEvent has been received for monitor with ID: ${event.monitor.id}")
            event.runWhenStateChanges { slackWebhookService.sendMessage(it.toSlackMessage()).handleResponse() }
        }
        eventDispatcher.subscribeToSSLWillExpireEvents { event ->
            logger.debug("An SSLWillExpireEvent has been received for monitor with ID: ${event.monitor.id}")
            event.runWhenStateChanges { slackWebhookService.sendMessage(it.toSlackMessage()).handleResponse() }
        }
    }

    private fun UptimeMonitorEvent.toSlackMessage() = SlackWebhookMessage(text = toMessage())

    private fun SSLMonitorEvent.toSlackMessage() = SlackWebhookMessage(text = toMessage())

    private fun Flowable<HttpResponse<String>>.handleResponse() =
        subscribe(
            {
                logger.debug("A Slack message to your configured webhook has been successfully sent")
            },
            { ex ->
                if (ex is HttpClientResponseException) {
                    val responseBody = ex.response.getBody(String::class.java)
                    logger.error("Slack message cannot be sent to your configured webhook: $responseBody")
                }
            }
        )

    private fun UptimeMonitorEvent.toMessage(): String {
        val messageParts: List<String> = when (this) {
            is MonitorUpEvent -> toStructuredMessage().let { details ->
                listOfNotNull(
                    "${getEmoji()} *${details.summary}*",
                    "_${details.latency}_",
                    details.previousDownTime.orNull()
                )
            }
            is MonitorDownEvent -> toStructuredMessage().let { details ->
                listOfNotNull(
                    "${getEmoji()} *${details.summary}*",
                    details.previousUpTime.orNull()
                )
            }
        }

        return messageParts.joinToString("\n")
    }

    private fun SSLMonitorEvent.toMessage(): String {
        val messageParts: List<String> = when (this) {
            is SSLValidEvent -> toStructuredMessage().let { details ->
                listOfNotNull(
                    "${getEmoji()} *${details.summary}*",
                    details.previousInvalidEvent.orNull()
                )
            }
            is SSLWillExpireEvent -> toStructuredMessage().let { details ->
                listOf(
                    "${getEmoji()} *${details.summary}*",
                    "_${details.validUntil}_"
                )
            }
            is SSLInvalidEvent -> toStructuredMessage().let { details ->
                listOfNotNull(
                    "${getEmoji()} *${details.summary}*",
                    "_${details.error}_",
                    details.previousValidEvent.orNull()
                )
            }
        }

        return messageParts.joinToString("\n")
    }
}
