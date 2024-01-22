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


    init {
        loadContent(currentPage)
        _isMaxArts.value = false
    }

    fun onPageFinished() {
        loadContent(++currentPage)
    }

    private fun loadContent(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artsService.getArtByPage(page)
            val totalNumber = arts.pagination?.total
            if (page != totalNumber) {
                _artsData.postValue(_artsData.value.orEmpty() + arts.data)
            } else {
                _isMaxArts.postValue(true)
            }
        }
    }
}
