package com.example.taskplayer.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class QuotesApiResponse(
    val success: Boolean,
    val data: List<QuotesResponse>
)