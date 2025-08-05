package com.example.taskplayer.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskplayer.data.provider.ResourceProvider
import com.example.taskplayer.domain.repository.AuthRepository

class MainViewModelFactory(
    private val repository: AuthRepository,
    private val resources: ResourceProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository, resources) as T
    }
}