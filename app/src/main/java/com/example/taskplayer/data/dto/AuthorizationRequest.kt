package com.example.taskplayer.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationRequest(
    val email: String,
    val password: String
)
