package com.example.taskplayer.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    val id: Int,
    val title: String,
    val image: String?,
    val description: String
)