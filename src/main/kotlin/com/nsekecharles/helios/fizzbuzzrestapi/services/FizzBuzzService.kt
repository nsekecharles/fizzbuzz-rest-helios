package com.nsekecharles.helios.fizzbuzzrestapi.services

import com.nsekecharles.helios.fizzbuzzrestapi.controller.FizzBuzzRequest
import com.nsekecharles.helios.fizzbuzzrestapi.ports.FizzBuzzGenerator
import com.nsekecharles.helios.fizzbuzzrestapi.ports.StatsGenerator
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class FizzBuzzService : FizzBuzzGenerator, StatsGenerator {

    private val stats: ConcurrentHashMap<FizzBuzzRequest, Int> = ConcurrentHashMap()

    override fun fizzBuzz(fizzBuzzRequest: FizzBuzzRequest): List<String> {
        stats[fizzBuzzRequest] = stats.getOrDefault(fizzBuzzRequest, 0) + 1

        return List(fizzBuzzRequest.limit) { i ->
            val num = i + 1
            when {
                num % (fizzBuzzRequest.fizzDivider * fizzBuzzRequest.buzzDivider) == 0 -> fizzBuzzRequest.fizzLabel + fizzBuzzRequest.buzzLabel
                num % fizzBuzzRequest.fizzDivider == 0 -> fizzBuzzRequest.fizzLabel
                num % fizzBuzzRequest.buzzDivider == 0 -> fizzBuzzRequest.buzzLabel
                else -> num.toString()
            }
        }
    }

    override fun getMostUsedRequest(): Map<String, Any> {

        if (stats.isEmpty()) {
            return mapOf(
                "int1" to "",
                "int2" to "",
                "limit" to "",
                "str1" to "",
                "str2" to "",
                "count" to ""
            )
        }

        val mostFrequentRequest = stats.maxBy { it.value }.key

        return mapOf(
            "int1" to mostFrequentRequest.fizzDivider,
            "int2" to mostFrequentRequest.buzzDivider,
            "limit" to mostFrequentRequest.limit,
            "str1" to mostFrequentRequest.fizzLabel,
            "str2" to mostFrequentRequest.buzzLabel,
            "count" to stats[mostFrequentRequest]!!
        )
    }
}