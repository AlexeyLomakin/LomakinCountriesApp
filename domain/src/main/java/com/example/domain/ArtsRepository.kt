package com.example.domain

import androidx.lifecycle.LiveData

interface ArtsRepository {
    suspend fun saveAllArts(page: Int)
    fun getAllArts(): LiveData<List<ArtsDomainEntity>>
}