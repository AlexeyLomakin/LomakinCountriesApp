package com.example.lomakincountriesapp.presentation.arts.artsviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lomakincountriesapp.data.arts.Arts
import com.example.lomakincountriesapp.network.ArtsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ArtsViewModel @Inject constructor(private val artsService: ArtsService) : ViewModel() {

    private val _artsData = MutableLiveData<List<Arts>>()
    val artsData: LiveData<List<Arts>> = _artsData
    private var currentPage = 1


    init {
        loadContent(currentPage)
    }

    fun loadMoreItems() {
        ++currentPage
        loadContent(currentPage)
    }

    private fun loadContent(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            val arts = artsService.getArtByPage(page)
            _artsData.value = _artsData.value.orEmpty() + arts.data

        }
    }
}