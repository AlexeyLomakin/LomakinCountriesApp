package com.example.domain.usecases

import com.example.domain.ArtsRepository
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

