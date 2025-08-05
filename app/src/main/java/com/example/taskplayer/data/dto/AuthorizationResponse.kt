package com.example.taskplayer.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationResponse(
    val id: String,
    val email: String,
    val nickName: String,
    val avatar: String?,
    val token: String
)
