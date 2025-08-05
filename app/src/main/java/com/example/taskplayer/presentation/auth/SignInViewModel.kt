package com.example.taskplayer.presentation.auth

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.domain.repository.AuthRepository

class SignInViewModel(
    private val tokenManager: UserSessionManager,
    private val authRepository: AuthRepository = AuthRepository(
        RetrofitClient.authService
    ),

    ) : ViewModel() {
    var signInState = mutableStateOf(
        SignInState(
            email = tokenManager.getLastEmail() ?: ""
        )
    )
        private set

    val emailHasError = derivedStateOf {
        signInState.value.emailTouched &&
                !android.util.Patterns.EMAIL_ADDRESS.matcher(signInState.value.email).matches()
    }

    fun setEmail(email: String) {
        signInState.value =
            signInState.value.copy(email = email, emailTouched = true, errorMessage = null)
    }

    fun setPassword(password: String) {
        signInState.value = signInState.value.copy(password = password)
    }


    suspend fun login(): Boolean {

        if (signInState.value.email.isBlank() || signInState.value.password.isBlank()) {
            signInState.value = signInState.value.copy(
                errorMessage = "Заполните все поля"
            )
            return false
        }

        if (emailHasError.value) {
            signInState.value = signInState.value.copy(
                errorMessage = "Некорректный email"
            )
            return false
        }

        try {
            signInState.value = signInState.value.copy(isLoading = true, errorMessage = null)
            val response = authRepository.login(
                signInState.value.email,
                signInState.value.password,
            )


            tokenManager.saveAuthData(
                token = response.token,
                userId = response.id,
                nickName = response.nickName,
                avatar = response.avatar,
                email = response.email
            )

            return true

        } catch (e: Exception) {
            signInState.value = signInState.value.copy(
                errorMessage = e.message ?: "Ошибка"
            )
            Log.e("SignInViewModel", "Login failed: ${e.message}")
            return false
        } finally {
            signInState.value = signInState.value.copy(isLoading = false)
        }

    }
}