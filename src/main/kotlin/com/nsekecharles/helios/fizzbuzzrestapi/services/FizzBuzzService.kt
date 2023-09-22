package com.nsekecharles.helios.fizzbuzzrestapi.services

import com.nsekecharles.helios.fizzbuzzrestapi.controller.FizzBuzzRequest
import com.nsekecharles.helios.fizzbuzzrestapi.ports.FizzBuzzGenerator
import com.nsekecharles.helios.fizzbuzzrestapi.ports.StatsGenerator
import org.springframework.stereotype.Service

@Service
class FizzBuzzService : FizzBuzzGenerator, StatsGenerator {
    override fun fizzBuzz(fizzBuzzRequest: FizzBuzzRequest): List<String> {
        return listOf("fizzbuzz")
    }

    override fun getMostUsedRequest(): Map<String, Any> {
        return mapOf(
            "int1" to 2,
            "int2" to 3,
            "limit" to 4,
            "str1" to "Fizz",
            "str2" to "Buzz",
            "count" to 10
        )
    }
}