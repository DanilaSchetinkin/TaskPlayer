package com.example.taskplayer.data.remote.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRemoteSource {
    @POST("user/login")
    suspend fun login(@Body authorizationRequest: AuthorizationRequest): AuthorizationResponse
}