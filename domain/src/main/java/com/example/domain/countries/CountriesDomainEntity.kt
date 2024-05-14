package com.example.domain.countries

import java.math.BigDecimal

data class CountriesDomainEntity(
    val id: Long,
    val name: String?,
    val capital: String?,
    val languages: String?,
    val area: BigDecimal?,
    val population: ULong?,
    val flags: String?,
)
