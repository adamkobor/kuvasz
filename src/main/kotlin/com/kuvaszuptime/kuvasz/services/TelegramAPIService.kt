package com.kuvaszuptime.kuvasz.services

import com.kuvaszuptime.kuvasz.config.handlers.TelegramEventHandlerConfig
import com.kuvaszuptime.kuvasz.models.handlers.TelegramAPIMessage
import io.micronaut.context.annotation.Requires
import io.micronaut.context.event.ShutdownEvent
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.event.annotation.EventListener
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Requires(property = "handler-config.telegram-event-handler.enabled", value = "true")
class TelegramAPIService @Inject constructor(
    private val telegramEventHandlerConfig: TelegramEventHandlerConfig,
    private val httpClient: RxHttpClient
) : TextMessageService {

    companion object {
        internal const val RETRY_COUNT = 3L
    }

    override fun sendMessage(content: String): Flowable<HttpResponse<String>> {
        val message = TelegramAPIMessage(chat_id = telegramEventHandlerConfig.chatId, text = content)
        val url = "https://api.telegram.org/bot" + telegramEventHandlerConfig.token + "/sendMessage"
        val request: HttpRequest<TelegramAPIMessage> = HttpRequest.POST(url, message)

        return httpClient
            .exchange(request, Argument.STRING, Argument.STRING)
            .retry(RETRY_COUNT)
    }

    @EventListener
    @Suppress("UNUSED_PARAMETER")
    internal fun onShutdownEvent(event: ShutdownEvent) {
        httpClient.close()
    }
}
