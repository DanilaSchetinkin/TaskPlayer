package com.example.taskplayer.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.domain.repository.AuthRepository

class SignInViewModelFactory(
    private val tokenManager: UserSessionManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(
                authRepository = AuthRepository(RetrofitClient.authService),
                tokenManager = tokenManager
            ) as T
        }
        throw IllegalArgumentException("Неизвестный ViewModel")
    }
}