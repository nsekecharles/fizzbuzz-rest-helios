package com.nsekecharles.helios.fizzbuzzrestapi.controller

import jakarta.validation.constraints.Min

data class FizzBuzzRequest(
    @field:Min(1, message = "Le diviseur des fizz doit être supérieure ou égale à 1")
    val fizzDivider: Int,
    @field:Min(1, message = "Le diviseur des buzz doit être supérieure ou égale à 1")
    val buzzDivider: Int,
    @field:Min(1, message = "La limite doit être supérieure ou égale à 1")
    val limit: Int,
    val fizzLabel: String = "Fizz",
    val buzzLabel: String = "Buzz"
)