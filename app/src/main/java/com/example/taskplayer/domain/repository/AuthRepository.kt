package com.example.taskplayer.domain.repository

import android.util.Log
import com.example.taskplayer.data.remote.AuthRemoteSource
import com.example.taskplayer.data.dto.AuthorizationRequest
import com.example.taskplayer.data.dto.AuthorizationResponse
import com.example.taskplayer.data.provider.ResourceProvider
import com.example.taskplayer.domain.model.FeelingsResponse
import com.example.taskplayer.domain.model.QuotesResponse

class AuthRepository(
    private val authServiceProvider: () -> AuthRemoteSource
) {
    private val authService: AuthRemoteSource get() = authServiceProvider()


    suspend fun login(email: String, password: String): AuthorizationResponse {
        val request = AuthorizationRequest(email, password)
        return authService.login(request)
    }

    suspend fun getFeelings(): List<FeelingsResponse> {
        return try {
            val response = authService.getFeelings()
            Log.d("Repository", "FeelingsApiResponse: $response")
            Log.d("Repository", "API feelings count: ${response.data.size}")
            response.data.sortedBy { it.position }
        } catch (e: Exception) {
            Log.e("Repository", "Error in getFeelings", e)
            emptyList()
        }
    }

    suspend fun getQuotes(): List<QuotesResponse> {
        return try {
            authService.getQuotes().data
        } catch (e: Exception) {
            emptyList()
        }
    }
}