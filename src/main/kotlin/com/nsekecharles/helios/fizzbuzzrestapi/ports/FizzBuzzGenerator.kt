package com.nsekecharles.helios.fizzbuzzrestapi.ports

import com.nsekecharles.helios.fizzbuzzrestapi.controller.FizzBuzzRequest

interface FizzBuzzGenerator {
    fun fizzBuzz(fizzBuzzRequest: FizzBuzzRequest): List<String>
}