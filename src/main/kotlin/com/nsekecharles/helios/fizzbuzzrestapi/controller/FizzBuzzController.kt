package com.nsekecharles.helios.fizzbuzzrestapi.controller

import com.nsekecharles.helios.fizzbuzzrestapi.ports.FizzBuzzGenerator
import com.nsekecharles.helios.fizzbuzzrestapi.ports.StatsGenerator
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FizzBuzzController(
    val fizzBuzzService: FizzBuzzGenerator,
    val statsGenerator: StatsGenerator
) {

    @PostMapping("/fizzbuzz")
    fun getFizzBuzz(@Valid @RequestBody fizzBuzzRequest: FizzBuzzRequest): List<String> {
        return fizzBuzzService.fizzBuzz(fizzBuzzRequest)
    }

    @GetMapping("/fizzbuzz/mostUsedRequest")
    fun getMostUsedRequest(): Map<String, Any> {
        return statsGenerator.getMostUsedRequest()
    }

}