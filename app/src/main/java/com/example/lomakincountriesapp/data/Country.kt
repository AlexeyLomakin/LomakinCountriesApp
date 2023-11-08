package com.example.lomakincountriesapp.data

import com.example.lomakincountriesapp.network.model.Flags
import com.example.lomakincountriesapp.network.model.Languages
import com.example.lomakincountriesapp.network.model.Name

data class Country(
    var name: Name? = Name(),
    var capital: ArrayList<String>,
    var languages: Languages? = Languages(),
    var area: Double,
    var population: Int,
    var flags: Flags? = Flags(),
)
