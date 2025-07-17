package com.example.taskplayer.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationRequest(
    val email: String,
    val password: String
)
