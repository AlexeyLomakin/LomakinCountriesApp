package com.example.presentation.arts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.domain.ArtsDomainEntity
import com.example.domain.ArtsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel @Inject constructor(private val artRepository: ArtsRepository) : ViewModel() {

    private val _artsData = MutableLiveData<List<ArtsDomainEntity>>()
    val artsData: LiveData<List<ArtsDomainEntity>> = _artsData
    private var _isMaxArts = MutableLiveData<Boolean>()
    val isMaxArts: LiveData<Boolean> = _isMaxArts


    init {
       loadFirstPage()
        _isMaxArts.value = false
    }

    fun onPageFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artRepository.getAllArts()
            val isAllPagesLoaded = arts.map { list ->
                list.all {
                    it.currentPage != it.totalPage
                }
            }
            if (isAllPagesLoaded.value == true){
                _isMaxArts.postValue(true)
            } else {
                loadContent()
            }
        }
    }


    private fun loadFirstPage() {
        viewModelScope.launch(Dispatchers.IO) {
            artRepository.saveAllArts(1)
        }
    }

    private fun loadContent() {
        viewModelScope.launch(Dispatchers.IO) {
            val arts = artRepository.getAllArts()
            _artsData.postValue(_artsData.value.orEmpty() + arts.value.orEmpty())
        }
    }
}

