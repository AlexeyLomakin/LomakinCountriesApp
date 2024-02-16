package com.example.data.room

import com.example.domain.ArtsDomainEntity
import javax.inject.Inject

interface Mapper<I,O> {
    fun map(input: I): O
}

class RoomArtsMapper @Inject constructor() : Mapper<ArtsRoomEntity, ArtsDomainEntity>{
    override fun map(input: ArtsRoomEntity): ArtsDomainEntity {
       return ArtsDomainEntity(
           id = input.artId ,
           title = input.title,
           totalPage = input.totalPage,
           artistDisplay = input.artistDisplay,
           imageUrl = input.imageUrl,
           currentPage = input.currentPage
       )
    }
}