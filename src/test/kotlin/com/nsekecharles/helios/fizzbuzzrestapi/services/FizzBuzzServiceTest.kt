package com.nsekecharles.helios.fizzbuzzrestapi.services

import com.nsekecharles.helios.fizzbuzzrestapi.controller.FizzBuzzRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FizzBuzzServiceTest {
    val fizzBuzzService: FizzBuzzService = FizzBuzzService()

    @Test
    fun `should return fizz`() {

        val fizzBuzzRequest = FizzBuzzRequest(1, 4, 1)

        val fizzBuzz = fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        assertThat(fizzBuzz).containsOnly("Fizz")
    }

    @Test
    fun `should return buzz`() {

        val fizzBuzzRequest = FizzBuzzRequest(2, 1, 1)

        val fizzBuzz = fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        assertThat(fizzBuzz).containsOnly("Buzz")
    }

    @Test
    fun `should return fizzbuzz`() {

        val fizzBuzzRequest = FizzBuzzRequest(1, 1, 1)

        val fizzBuzz = fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        assertThat(fizzBuzz).containsOnly("FizzBuzz")
    }

    @Test
    fun `should return valid suite given a limit of 5`() {

        val fizzBuzzRequest = FizzBuzzRequest(3, 5, 5)

        val fizzBuzz = fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        assertThat(fizzBuzz).containsOnly("1", "2", "Fizz", "4", "Buzz")
    }

    @Test
    fun `should return valid suite given a limit of 15`() {

        val fizzBuzzRequest = FizzBuzzRequest(3, 5, 15)

        val fizzBuzz = fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        assertThat(fizzBuzz).containsOnly(
            "1",
            "2",
            "Fizz",
            "4",
            "Buzz",
            "Fizz",
            "7",
            "8",
            "Fizz",
            "Buzz",
            "11",
            "Fizz",
            "13",
            "14",
            "FizzBuzz"
        )
    }

    @Test
    fun `should return valid suite with custom labels given a limit of 15`() {

        val fizzBuzzRequest = FizzBuzzRequest(3, 5, 15, "testFizz", "testBuzz")

        val fizzBuzz = fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        assertThat(fizzBuzz).containsOnly(
            "1",
            "2",
            "testFizz",
            "4",
            "testBuzz",
            "testFizz",
            "7",
            "8",
            "testFizz",
            "testBuzz",
            "11",
            "testFizz",
            "13",
            "14",
            "testFizztestBuzz"
        )
    }

    @Test
    fun `should return empty stats`() {

        val mostUsedRequest = fizzBuzzService.getMostUsedRequest()

        assertThat(mostUsedRequest).isEqualTo(
            mapOf(
                "int1" to "",
                "int2" to "",
                "limit" to "",
                "str1" to "",
                "str2" to "",
                "count" to ""
            )
        )
    }

    @Test
    fun `should return most used request`() {

        var fizzBuzzRequest = FizzBuzzRequest(3, 5, 15, "testFizz", "testBuzz")
        fizzBuzzService.fizzBuzz(fizzBuzzRequest)
        fizzBuzzRequest = FizzBuzzRequest(3, 5, 15, "testFizz", "testBuzz")
        fizzBuzzService.fizzBuzz(fizzBuzzRequest)
        fizzBuzzRequest = FizzBuzzRequest(3, 5, 15, "Fizz", "Buzz")
        fizzBuzzService.fizzBuzz(fizzBuzzRequest)

        val mostUsedRequest = fizzBuzzService.getMostUsedRequest()

        assertThat(mostUsedRequest).isEqualTo(
            mapOf(
                "int1" to 3,
                "int2" to 5,
                "limit" to 15,
                "str1" to "testFizz",
                "str2" to "testBuzz",
                "count" to 2
            )
        )
    }
}