package com.example.lomakincountriesapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lomakincountriesapp.data.Arts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtsViewModel : ViewModel() {

    private val _artsData = MutableLiveData<List<Arts>>()
    val artsData: LiveData<List<Arts>> = _artsData
    private var currentPage = 1
    private var isLoading = false
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadContent(currentPage)
    }

     fun loadMoreItems() {
        if (!isLoading) {
            isLoading = true
            ++currentPage
            loadContent(currentPage)
        }
    }

    private fun loadContent(page: Int) {
        viewModelScope.launch {
            try {
                val arts = withContext(Dispatchers.IO) {
                    ArtsModule().provideArtsService().getArtByPage(page)
                }
                _artsData.value = _artsData.value.orEmpty() + arts.data
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки данных"
            } finally {
                isLoading = false
            }
        }
    }
}