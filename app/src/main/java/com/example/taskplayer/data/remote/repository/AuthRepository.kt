package com.example.taskplayer.data.remote.repository

import android.util.Log
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.data.remote.auth.AuthRemoteSource
import com.example.taskplayer.data.remote.auth.AuthorizationRequest
import com.example.taskplayer.data.remote.auth.AuthorizationResponse
import com.example.taskplayer.data.remote.auth.FeelingsResponse
import com.example.taskplayer.data.remote.auth.QuotesResponse

class AuthRepository(private val authService: AuthRemoteSource, private val tokenManager: TokenManager){


    suspend fun login(email: String, password: String): AuthorizationResponse {
        val request = AuthorizationRequest(email, password)
        return authService.login(request)
    }
    suspend fun getFeelings(): List<FeelingsResponse>{
        return try {
            val token = "Bearer ${tokenManager.getToken()}"
            val response = authService.getFeelings(token)
            Log.d("Repository", "FeelingsApiResponse: $response")
            Log.d("Repository", "API feelings count: ${response.data.size}")
            response.data.sortedBy { it.position }
        } catch (e:Exception) {
            Log.e("Repository", "Error in getFeelings", e)
            emptyList()
        }
    }

    suspend fun getQuotes(): List<QuotesResponse>{
        return try {
            val token = "Bearer ${tokenManager.getToken()}"
            val response = authService.getQuotes(token)
            response.data
        } catch (e:Exception){
            emptyList()
        }
    }
}