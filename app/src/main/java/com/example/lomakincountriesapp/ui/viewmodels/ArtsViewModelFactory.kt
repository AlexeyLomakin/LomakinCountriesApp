package com.example.lomakincountriesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lomakincountriesapp.network.ArtsService

class ArtsViewModelFactory(private val artsService: ArtsService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtsViewModel::class.java)) {
            return ArtsViewModel(artsService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}