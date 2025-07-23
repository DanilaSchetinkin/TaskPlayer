package com.example.taskplayer.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class FeelingsResponse(
    val id: Int,
    val image: String?,
    val title: String,
    val position: Int
)

