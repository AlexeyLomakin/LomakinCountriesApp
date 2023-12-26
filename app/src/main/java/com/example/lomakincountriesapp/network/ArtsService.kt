package com.example.lomakincountriesapp.network

import com.example.lomakincountriesapp.data.Artworks
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtsService {

    @GET("artworks")
    suspend fun getArtByPage(
        @Query("page") currentPage: Int?
    ): Artworks
}