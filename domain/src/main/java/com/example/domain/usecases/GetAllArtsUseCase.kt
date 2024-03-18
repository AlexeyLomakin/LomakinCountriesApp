package com.example.domain.usecases

import androidx.lifecycle.LiveData
import com.example.domain.ArtsDomainEntity
import com.example.domain.ArtsRepository
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