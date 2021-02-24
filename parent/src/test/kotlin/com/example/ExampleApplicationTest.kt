package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class ExampleApplicationTest {
    @Test
    fun requestActuatorWhenNotAuthenticatedThen401(@Autowired rest: TestRestTemplate) {
        val info = rest.getForEntity("/actuator/info", String.javaClass)
        assertThat(info.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun requestChildContextWhenNotAuthenticatedThen401(@Autowired rest: TestRestTemplate) {
        val info = rest.getForEntity("/lizard/feed", String.javaClass)
        assertThat(info.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
    }
}