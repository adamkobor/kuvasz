package com.kuvaszuptime.kuvasz.security

import com.kuvaszuptime.kuvasz.config.AdminAuthConfig
import com.kuvaszuptime.kuvasz.mocks.generateCredentials
import com.kuvaszuptime.kuvasz.testutils.getBean
import com.kuvaszuptime.kuvasz.testutils.getLowLevelClient
import com.kuvaszuptime.kuvasz.testutils.startTestApplication
import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken

class AuthenticationTest : BehaviorSpec({
    val service = startTestApplication(withRealAuth = true)
    val client = service.getLowLevelClient()
    val authConfig = service.getBean<AdminAuthConfig>()

    given("a public endpoint") {

        `when`("an anonymous user calls it") {
            val response = client.toBlocking().exchange<Any>("/health")
            then("it should return 200") {
                response.status shouldBe HttpStatus.OK
            }
        }
    }
    given("the login endpoint") {

        `when`("the user provides the right credentials") {
            val credentials = generateCredentials(authConfig, valid = true)
            val request = HttpRequest.POST("/login", credentials)
            val response = client.toBlocking().exchange(request, BearerAccessRefreshToken::class.java)
            val token = response.body()!!
            val parsedJwt = JWTParser.parse(token.accessToken)
            then("it should return a signed access token for the given user") {
                response.status shouldBe HttpStatus.OK
                token.username shouldBe credentials.username
                token.accessToken shouldNotBe null
                (parsedJwt is SignedJWT) shouldBe true
            }
        }

        `when`("a user provides bad credentials") {
            val credentials = generateCredentials(authConfig, valid = false)
            val request = HttpRequest.POST("/login", credentials)
            val exception = shouldThrow<HttpClientResponseException> {
                client.toBlocking().exchange(request, BearerAccessRefreshToken::class.java)
            }
            then("it should return 401") {
                exception.status shouldBe HttpStatus.UNAUTHORIZED
            }
        }
    }
    given("an authenticated endpoint") {

        `when`("an anonymous user calls it") {
            val exception = shouldThrow<HttpClientResponseException> {
                client.toBlocking().exchange<Any>("/monitor")
            }
            then("it should return 401") {
                exception.status shouldBe HttpStatus.UNAUTHORIZED
            }
        }

        `when`("a user provides the right credentials") {
            val credentials = generateCredentials(authConfig, valid = true)
            val loginRequest = HttpRequest.POST("/login", credentials)
            val loginResponse = client.toBlocking().exchange(loginRequest, BearerAccessRefreshToken::class.java)
            val token = loginResponse.body()!!

            val request = HttpRequest.GET<Any>("/monitor").bearerAuth(token.accessToken)
            val response = client.toBlocking().exchange<Any, Any>(request)
            then("it should return 200") {
                response.status shouldBe HttpStatus.OK
            }
        }
    }
})
