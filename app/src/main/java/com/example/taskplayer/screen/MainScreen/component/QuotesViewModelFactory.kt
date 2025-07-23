package com.example.taskplayer.screen.MainScreen.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskplayer.data.remote.repository.AuthRepository

class QuotesViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return QuotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Неизвестный ViewModel")
    }
}