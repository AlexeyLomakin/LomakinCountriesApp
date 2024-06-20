package com.example.domain.arts

import androidx.lifecycle.LiveData

interface ArtsRepository {
    suspend fun saveAllArts(page: Int)
    fun getAllArts(): LiveData<List<ArtsDomainEntity>>
}