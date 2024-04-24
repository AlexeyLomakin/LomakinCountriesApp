package com.example.presentation.countries

import com.example.presentation.countries.dataclasses.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String
    ): Response<List<Country>>

    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}