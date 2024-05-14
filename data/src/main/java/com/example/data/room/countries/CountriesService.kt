package com.example.data.room.countries

import dataclasses.arts.countries.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountriesService {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>>
}