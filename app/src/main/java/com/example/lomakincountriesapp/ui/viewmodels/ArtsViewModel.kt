package com.example.lomakincountriesapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lomakincountriesapp.data.Arts
import com.example.lomakincountriesapp.network.ArtsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtsViewModel(private val artsService: ArtsService) : ViewModel() {

    val artsData = MutableLiveData<List<Arts>>()
    private var currentPage = 1

    init {
        loadContent(currentPage)
    }

    fun loadMoreItems() {
        ++currentPage
        loadContent(currentPage)
    }

    private fun loadContent(page: Int) {
        viewModelScope.launch {
            val arts = withContext(Dispatchers.IO) {
                artsService.getArtByPage(page)
            }
            artsData.value = arts.data
        }
    }
}