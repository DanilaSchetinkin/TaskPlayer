package com.example.taskplayer.data.remote.auth

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthRemoteSource {
    @POST("user/login")
    suspend fun login(@Body authorizationRequest: AuthorizationRequest): AuthorizationResponse

    @GET("feelings")
    suspend fun getFeelings(
        @Header("Authorization") token: String
    ): FeelingsApiResponse

    @GET("quotes")
    suspend fun getQuotes(
        @Header("Authorization") token: String
    ): QuotesApiResponse
}
