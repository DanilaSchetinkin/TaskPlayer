package com.example.taskplayer.presentation.main.feelings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskplayer.domain.repository.AuthRepository

class FeelingsViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeelingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeelingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Неизвестный ViewModel")
    }
}