package com.example.domain.arts.usecases

import androidx.lifecycle.LiveData
import com.example.domain.arts.ArtsDomainEntity
import com.example.domain.arts.ArtsRepository
import javax.inject.Inject

class GetAllArtsUseCaseImpl @Inject constructor(
    private val artsRepository: ArtsRepository
) : GetAllArtsUseCase {

    override fun invoke() : LiveData<List<ArtsDomainEntity>> {
        return artsRepository.getAllArts()
    }
}

interface GetAllArtsUseCase {
    operator fun invoke() : LiveData<List<ArtsDomainEntity>>

}