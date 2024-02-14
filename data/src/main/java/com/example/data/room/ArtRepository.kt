package com.example.data.room

import com.example.domain.ArtsDomain
import com.example.domain.ArtsRepositoryInterface

class ArtRepository(): ArtsRepositoryInterface {
    override suspend fun getAllArtsFromApi(page: Int): List<ArtsDomain> {
        try {

            val response = artsService.getAllArts()

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

        } catch (e: Exception) {
        }
    }


    override fun showAllArts(): List<ArtsDomain> {
        return artsDao.getAllArts()
    }
}
