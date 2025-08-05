package com.example.taskplayer.presentation.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskplayer.data.local.UserSessionManager

class ProfileViewModelFactory(private val userSessionManager: UserSessionManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(userSessionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}