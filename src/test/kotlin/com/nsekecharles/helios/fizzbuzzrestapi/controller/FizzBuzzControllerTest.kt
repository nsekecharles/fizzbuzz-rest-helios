package com.nsekecharles.helios.fizzbuzzrestapi.controller

import com.nsekecharles.helios.fizzbuzzrestapi.FizzbuzzRestApiApplication
import com.nsekecharles.helios.fizzbuzzrestapi.ports.FizzBuzzGenerator
import com.nsekecharles.helios.fizzbuzzrestapi.ports.StatsGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(
    classes = arrayOf(FizzbuzzRestApiApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class FizzBuzzControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @MockBean
    lateinit var fizzBuzzGenerator: FizzBuzzGenerator

    @MockBean
    lateinit var statsGenerator: StatsGenerator

    @Test
    fun `should return fizzbuzz when all input are ok`() {

        val fizzBuzzRequest = FizzBuzzRequest(fizzDivider = 3, buzzDivider = 5, limit = 3)
        `when`(fizzBuzzGenerator.fizzBuzz(fizzBuzzRequest)).thenReturn(listOf("fizzbuzz"))

        val result = testRestTemplate.postForEntity(
            "/fizzbuzz",
            fizzBuzzRequest,
            String::class.java
        )

        assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result?.body).isEqualTo("[\"fizzbuzz\"]")
    }

    @Test
    fun `should return a bad request when fizz divider is equal to 0`() {

        val fizzBuzzRequest = FizzBuzzRequest(fizzDivider = 0, buzzDivider = 5, limit = 1)
        `when`(fizzBuzzGenerator.fizzBuzz(fizzBuzzRequest)).thenReturn(listOf("fizzbuzz"))

        val result = testRestTemplate.postForEntity(
            "/fizzbuzz",
            fizzBuzzRequest,
            String::class.java
        )

        assertThat(result?.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `should return a bad request when buzz divider is equal to 0`() {

        val fizzBuzzRequest = FizzBuzzRequest(fizzDivider = 2, buzzDivider = 0, limit = 1)
        `when`(fizzBuzzGenerator.fizzBuzz(fizzBuzzRequest)).thenReturn(listOf("fizzbuzz"))

        val result = testRestTemplate.postForEntity(
            "/fizzbuzz",
            fizzBuzzRequest,
            String::class.java
        )

        assertThat(result?.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `should return a bad request when limit is equal to 0`() {

        val fizzBuzzRequest = FizzBuzzRequest(fizzDivider = 2, buzzDivider = 1, limit = 0)
        `when`(fizzBuzzGenerator.fizzBuzz(fizzBuzzRequest)).thenReturn(listOf("fizzbuzz"))

        val result = testRestTemplate.postForEntity(
            "/fizzbuzz",
            fizzBuzzRequest,
            String::class.java
        )

        assertThat(result?.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `should return the most used request`() {

        val expectedMostUsedRequest = mapOf(
            "int1" to 2,
            "int2" to 3,
            "limit" to 4,
            "str1" to "Fizz",
            "str2" to "Buzz",
            "count" to 10
        )
        `when`(statsGenerator.getMostUsedRequest()).thenReturn(expectedMostUsedRequest)

        val result = testRestTemplate.getForEntity("/fizzbuzz/mostUsedRequest", Map::class.java)

        assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result?.body).isEqualTo(expectedMostUsedRequest)
    }

}