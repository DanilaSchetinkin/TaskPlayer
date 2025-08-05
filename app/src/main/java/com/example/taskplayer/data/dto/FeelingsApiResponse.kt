package com.example.taskplayer.data.dto

import com.example.taskplayer.domain.model.FeelingsResponse
import kotlinx.serialization.Serializable

@Serializable
data class FeelingsApiResponse(
    val success: Boolean,
    val data: List<FeelingsResponse>
)
