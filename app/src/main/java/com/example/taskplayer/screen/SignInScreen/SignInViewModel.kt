package com.example.taskplayer.screen.SignInScreen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.data.remote.repository.AuthRepository

class SignInViewModel(
    private val authRepository: AuthRepository = AuthRepository(RetrofitClient.authService)
): ViewModel(){
    var signInState = mutableStateOf(SignInState())
        private set

    val emailHasError = derivedStateOf {
        signInState.value.emailTouched &&
        !android.util.Patterns.EMAIL_ADDRESS.matcher(signInState.value.email).matches()
    }

    fun setEmail(email: String){
        signInState.value = signInState.value.copy(email = email, emailTouched = true)
    }
    fun  setPassword(password: String){
        signInState.value = signInState.value.copy(password = password)
    }
}