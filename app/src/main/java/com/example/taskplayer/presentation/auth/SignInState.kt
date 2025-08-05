package com.example.taskplayer.presentation.auth

data class SignInState(
    var email: String = "",
    var password: String = "",
    var errorMessage:String? = null,
    var emailTouched: Boolean = false,
    var isLoading: Boolean = false
)