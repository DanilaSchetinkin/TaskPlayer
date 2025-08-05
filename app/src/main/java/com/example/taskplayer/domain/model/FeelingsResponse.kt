package com.example.taskplayer.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FeelingsResponse(
    val id: Int,
    val image: String?,
    val title: String,
    val position: Int
)

