package com.example.taskplayer.screen.SignInScreen

data class SignInState(
    var email: String = "",
    var password: String = "",
    var errorMessage:String? = null,
    var emailTouched: Boolean = false
)