package com.example.lomakincountriesapp.network

import com.example.lomakincountriesapp.data.Page
import retrofit2.http.GET
import retrofit2.http.Query

interface PaginationService {

    @GET("artworks")
    suspend fun getPageById(
        @Query("page") currentPage: Int?
    ): Page
}