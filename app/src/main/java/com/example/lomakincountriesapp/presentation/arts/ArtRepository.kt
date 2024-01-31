package com.example.lomakincountriesapp.presentation.arts

import com.example.lomakincountriesapp.data.arts.ArtsEntity
import com.example.lomakincountriesapp.network.ArtsService
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artsDao: ArtsDao,
    private val artsService: ArtsService
) {
    suspend fun refreshArts(page: Int): List<ArtsEntity> {
        try {
            val response = artsService.getArtByPage(page)

            val artsEntityList = response.data.map { list ->
                ArtsEntity(
                    title = list.title,
                    artistDisplay = list.artist_display,
                    imageUrl = list.image_id,
                    totalPage = response.pagination?.total,
                    currentPage = response.pagination?.current_page,
                )

            }
            artsDao.insertArt(artsEntityList)
            return artsEntityList

        } catch (e: Exception) {
            return emptyList()
        }
    }

    fun getAllArts(): List<ArtsEntity> {
        return artsDao.getAllArts()
    }
}
