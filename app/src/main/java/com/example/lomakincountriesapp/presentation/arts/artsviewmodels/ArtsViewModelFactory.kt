package com.example.lomakincountriesapp.presentation.arts.artsviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lomakincountriesapp.network.ArtsService
import javax.inject.Inject

class ArtsViewModelFactory @Inject constructor(private val artsService: ArtsService) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtsViewModel(artsService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}