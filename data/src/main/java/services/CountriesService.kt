package services

import dataclasses.countries.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String
    ): List<Country>

    @GET("all")
    suspend fun getAllCountries(): List<Country>
}