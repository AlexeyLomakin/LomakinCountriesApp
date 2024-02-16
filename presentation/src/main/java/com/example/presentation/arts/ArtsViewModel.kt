package com.example.presentation.arts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ArtsDomainEntity
import com.example.domain.ArtsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(private val artRepository: ArtsRepository) : ViewModel() {

    private val _isMaxArts = MutableLiveData<Boolean>()
    val isMaxArts: LiveData<Boolean> = _isMaxArts
    private var currentPage: Int = 1

    val artsData: LiveData<List<ArtsDomainEntity>> = artRepository.getAllArts()

    init {
        loadFirstPage()
        _isMaxArts.value = false
    }

    fun onPageFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artRepository.getAllArts().value
            val areAllPagesNotLoaded = arts?.firstOrNull { currentPage == it.totalPage } == null
            if (areAllPagesNotLoaded) {
                _isMaxArts.postValue(true)
            } else {
                loadPages(++currentPage)
            }
        }
    }

    private fun loadFirstPage() {
        viewModelScope.launch(Dispatchers.IO) {
            artRepository.saveAllArts(currentPage)
        }
    }

    private fun loadPages(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            artRepository.saveAllArts(page)
        }
    }
}


