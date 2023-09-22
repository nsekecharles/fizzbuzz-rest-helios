package com.nsekecharles.helios.fizzbuzzrestapi.ports

interface StatsGenerator {
    fun getMostUsedRequest(): Map<String, Any>
}