package com.kuvaszuptime.kuvasz.handlers

import com.kuvaszuptime.kuvasz.services.EventDispatcher
import com.kuvaszuptime.kuvasz.services.SlackWebhookService
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Requires
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Context
@Requires(property = "handler-config.slack-event-handler.enabled", value = "true")
class SlackEventHandler @Inject constructor(
    slackWebhookService: SlackWebhookService,
    eventDispatcher: EventDispatcher
) : TextMessageEventHandler(eventDispatcher, slackWebhookService) {

    override val logger = LoggerFactory.getLogger(SlackEventHandler::class.java)

    override fun String.bold(): String = "*$this*"

    override fun String.italic(): String = "_{$this}_"
}
