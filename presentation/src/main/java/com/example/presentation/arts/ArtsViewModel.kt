package com.example.presentation.arts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.arts.ArtsDomainEntity
import com.example.domain.arts.usecases.GetAllArtsUseCase
import com.example.domain.arts.usecases.SaveAllArtsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(
    getAllArtsUseCase: GetAllArtsUseCase,
    private val saveAllArtsUseCase: SaveAllArtsUseCase
) : ViewModel() {

    private var currentPage = 1
    private val _isMaxArts = MutableLiveData<Boolean>()
    val isMaxArts: LiveData<Boolean> = _isMaxArts
    val artsData: LiveData<List<ArtsDomainEntity>> = getAllArtsUseCase()
    private var totalPages: Int? = null

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> get() = _error

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
        _loading.postValue(true)
        _error.postValue(null)
        try {
            saveAllArtsUseCase(FIRST_PAGE_NUM)
        } catch (e: Exception) {
            _error.postValue("Failed to load data: ${e.message}")
        } finally {
            _loading.postValue(false)
        }
    }

    private suspend fun loadPages(page: Int) {
        _loading.postValue(true)
        _error.postValue(null)
        try {
            saveAllArtsUseCase(page)
            if (totalPages == null) {
                totalPages = artsData.value?.firstOrNull()?.totalPage
            }
        } catch (e: Exception) {
            _error.postValue("Failed to load data: ${e.message}")
        } finally {
            _loading.postValue(false)
        }
    }

    companion object {
        private const val FIRST_PAGE_NUM = 1
    }
}
