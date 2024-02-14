package services

import dataclasses.arts.Artworks
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtsService {

    @GET("artworks")
    suspend fun getAllArts(
        @Query("page") currentPage: Int?
    ): Artworks
}