package com.example.domain.arts.usecases

import com.example.domain.arts.ArtsRepository
import javax.inject.Inject

class SaveAllArtsUseCaseImpl @Inject constructor(
    private val artsRepository: ArtsRepository
): SaveAllArtsUseCase {

    override suspend fun invoke(page: Int){
        artsRepository.saveAllArts(page)
    }
}

interface SaveAllArtsUseCase {
    suspend operator fun invoke(page: Int)
}

