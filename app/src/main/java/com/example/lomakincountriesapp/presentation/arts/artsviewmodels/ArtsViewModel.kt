package com.example.lomakincountriesapp.presentation.arts.artsviewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lomakincountriesapp.data.arts.ArtsEntity
import com.example.lomakincountriesapp.presentation.arts.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(private val artRepository: ArtRepository) : ViewModel() {

    private val _artsData = MutableLiveData<List<ArtsEntity>>()
    val artsData: LiveData<List<ArtsEntity>> = _artsData
    private var _isMaxArts = MutableLiveData<Boolean>()
    val isMaxArts: LiveData<Boolean> = _isMaxArts


    init {
       loadFirstPage()
        _isMaxArts.value = false
    }

    fun onPageFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artRepository.getAllArts()
            val isAllPagesLoaded = arts.all { it.currentPage == it.totalPage }

            if (!isAllPagesLoaded) {
                loadContent()
            } else {
                _isMaxArts.postValue(true)
            }
        }
    }


    private fun loadFirstPage() {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artRepository.refreshArts(1)
            _artsData.postValue(_artsData.value.orEmpty() + arts)
        }
    }

    private fun loadContent() {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artRepository.getAllArts()
            _artsData.postValue(_artsData.value.orEmpty() + arts)
        }
    }
}

