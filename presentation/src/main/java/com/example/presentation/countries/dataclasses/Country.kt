package com.example.presentation.countries.dataclasses

data class Country(
    val name: Name?,
    val capital: ArrayList<String>,
    val languages: Map<String?, String?>,
    val area: Double,
    val population: Long,
    val flags: Flags?,
)
