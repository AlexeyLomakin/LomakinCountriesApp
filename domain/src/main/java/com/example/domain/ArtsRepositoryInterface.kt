package com.example.domain

interface ArtsRepositoryInterface {
    suspend fun getAllArtsFromApi(page: Int): List<ArtsDomain>
    fun showAllArts(): List<ArtsDomain>
}