package com.example.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.domain.ArtsDomainEntity
import com.example.domain.ArtsRepository
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    private val artsService: ArtsService,
    private val artsDao: ArtsDao,
    private val mapper: Mapper<ArtsRoomEntity, ArtsDomainEntity>
): ArtsRepository {
    override suspend fun saveAllArts(page: Int) {
        try {

            val response = artsService.getAllArts(page)

            val artsRoomEntityList = response.data.map { list ->
                ArtsRoomEntity(
                    title = list.title,
                    artistDisplay = list.artist_display,
                    imageUrl = list.image_id,
                    totalPage = response.pagination?.total,
                    currentPage = response.pagination?.current_page,
                )

            }
            artsDao.insertArt(artsRoomEntityList)

        } catch (e: Exception) {
        }
    }


    override fun getAllArts(): LiveData<List<ArtsDomainEntity>> {

        return artsDao.getAllArts().map{list ->
            list.map { entity ->
                mapper.map(entity)
            }
        }
    }
}
