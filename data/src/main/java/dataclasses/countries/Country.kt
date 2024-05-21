package dataclasses.countries

import java.math.BigDecimal

data class Country(
    val name: Name?,
    val capital: ArrayList<String>,
    val languages: Map<String?, String?>,
    val area: BigDecimal,
    val population: ULong,
    val flags: Flags?,
)
