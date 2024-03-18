package com.example.presentation.arts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ArtsDomainEntity
import com.example.domain.usecases.GetAllArtsUseCase
import com.example.domain.usecases.SaveAllArtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(
    getAllArtsUseCase: GetAllArtsUseCase,
    private val saveAllArtsUseCase: SaveAllArtsUseCase,
) : ViewModel() {

    private var currentPage = 1
    private val _isMaxArts = MutableLiveData<Boolean>()
    val isMaxArts: LiveData<Boolean> = _isMaxArts
    val artsData: LiveData<List<ArtsDomainEntity>> = getAllArtsUseCase()
    private var totalPages: Int? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadFirstPage()
        }
        _isMaxArts.value = false
    }

    fun onPageFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentPage == totalPages) {
                _isMaxArts.value = true
            } else {
                loadPages(++currentPage)
            }
        }
    }

    private suspend fun loadFirstPage() {
        saveAllArtsUseCase(FIRST_PAGE_NUM)
    }

    private suspend fun loadPages(page: Int) {
        saveAllArtsUseCase(page)
        if (totalPages == null) {
            totalPages = artsData.value?.firstOrNull()?.totalPage
        }
    }

    companion object {
        private const val FIRST_PAGE_NUM = 1
    }
}