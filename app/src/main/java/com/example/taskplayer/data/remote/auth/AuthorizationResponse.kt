package com.example.taskplayer.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val id: String,
    val email: String,
    val nickName: String,
    val avatar: String?,
    val token: String
)
