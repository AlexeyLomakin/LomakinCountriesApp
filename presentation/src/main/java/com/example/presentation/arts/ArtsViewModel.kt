package com.example.presentation.arts
import androidx.lifecycle.LiveData
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

    private var currentPage: Int = 1

    val artsData: LiveData<List<ArtsDomainEntity>> = getAllArtsUseCase()

    init {
        loadFirstPage()
    }

    fun onPageFinished() {
        viewModelScope.launch(Dispatchers.IO) {
            loadPages(++currentPage)
        }
    }

    private fun loadFirstPage() {
        viewModelScope.launch(Dispatchers.IO) {
            saveAllArtsUseCase(currentPage)
        }
    }

    private fun loadPages(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            saveAllArtsUseCase(page)
        }
    }
}