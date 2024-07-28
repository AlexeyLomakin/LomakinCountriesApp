package com.example.data.room

import com.example.data.room.arts.ArtsRoomEntity
import com.example.data.room.countries.CountriesRoomEntity
import com.example.domain.arts.ArtsDomainEntity
import com.example.domain.countries.CountriesDomainEntity
import javax.inject.Inject

interface Mapper<I,O> {
    fun map(input: I): O
}

class RoomArtsMapper @Inject constructor() : Mapper<ArtsRoomEntity, ArtsDomainEntity>{
    override fun map(input: ArtsRoomEntity): ArtsDomainEntity {
       return ArtsDomainEntity(
           id = input.id ,
           artId = input.artId,
           title = input.title,
           totalPage = input.totalPage,
           artistDisplay = input.artistDisplay,
           imageUrl = input.imageUrl,
           currentPage = input.currentPage
       )
    }
}
class RoomCountriesMapper @Inject constructor() : Mapper<CountriesRoomEntity, CountriesDomainEntity>{
    override fun map(input: CountriesRoomEntity): CountriesDomainEntity {
        return CountriesDomainEntity(
            id = input.id ,
            area = input.area,
            name = input.name,
            population = input.population?.toULong(),
            languages = input.languages,
            flags = input.flags,
            capital = input.capital
        )
    }
}