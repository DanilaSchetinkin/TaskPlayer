package com.example.taskplayer.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class FeelingsApiResponse(
    val success: Boolean,
    val data: List<FeelingsResponse>
)
