package com.example.lomakincountriesapp.presentation.arts.artsviewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lomakincountriesapp.data.arts.Arts
import com.example.lomakincountriesapp.network.ArtsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(private val artsService: ArtsService) : ViewModel() {

    private val _artsData = MutableLiveData<List<Arts>>()
    val artsData: LiveData<List<Arts>> = _artsData
    private var _isMaxArts = MutableLiveData<Boolean>()
    val isMaxArts: LiveData<Boolean> = _isMaxArts
    private var currentPage = 1
    private var totalPages : Int? = 0

    init {
        loadContent(currentPage)
        _isMaxArts.value = false
        viewModelScope.launch(Dispatchers.IO) {
             totalPages = artsService.getArtsTotalPages().pagination?.total
        }
    }

    fun onPageFinished() {
            if (currentPage != totalPages) {
                loadContent(++currentPage)
            } else {
                _isMaxArts.postValue(true)
            }
    }

    private fun loadContent(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artsService.getArtByPage(page)
            _artsData.postValue(_artsData.value.orEmpty() + arts.data)
        }

    }
}
