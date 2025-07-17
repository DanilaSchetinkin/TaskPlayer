package com.example.taskplayer.data.remote.repository

import com.example.taskplayer.data.remote.auth.AuthRemoteSource
import com.example.taskplayer.data.remote.auth.AuthorizationRequest
import com.example.taskplayer.data.remote.auth.AuthorizationResponse

class AuthRepository(private val authService: AuthRemoteSource){

    suspend fun login(email: String, paswword:String): AuthorizationResponse {
        val request = AuthorizationRequest(email, paswword)
        return authService.login(request)
    }
}