package com.example.lomakincountriesapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArtsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}