package com.example.taskplayer.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    val id: Int,
    val title: String,
    val image: String?,
    val description: String
)