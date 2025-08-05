package com.example.taskplayer.data.dto

import com.example.taskplayer.domain.model.QuotesResponse
import kotlinx.serialization.Serializable

@Serializable
data class QuotesApiResponse(
    val success: Boolean,
    val data: List<QuotesResponse>
)