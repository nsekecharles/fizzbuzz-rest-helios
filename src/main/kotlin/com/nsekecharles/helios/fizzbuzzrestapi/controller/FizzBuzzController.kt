package com.nsekecharles.helios.fizzbuzzrestapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FizzBuzzController {
    @GetMapping("/fizzbuzz")
    fun getFizzBuzz(): String {
        return "fizzbuzz"
    }
}