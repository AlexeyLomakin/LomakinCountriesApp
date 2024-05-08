package com.example.data.room


import dataclasses.arts.Artworks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ArtsService {

    @GET("artworks")
    suspend fun getAllArts(
        @Query("page") currentPage: Int?
    ): Response<Artworks>
}