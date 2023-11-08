package com.example.lomakincountriesapp.network

import com.example.lomakincountriesapp.data.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String
    ): List<Country>
    @GET("all")
    suspend fun getAllCountries():List<Country>
}