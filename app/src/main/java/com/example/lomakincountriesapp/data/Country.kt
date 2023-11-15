package com.example.lomakincountriesapp.data

data class Country(
    var name: Name?,
    var capital: ArrayList<String>,
    var languages: Map<String?, String?>,
    var area: Double,
    var population: Int,
    var flags: Flags?,
)
