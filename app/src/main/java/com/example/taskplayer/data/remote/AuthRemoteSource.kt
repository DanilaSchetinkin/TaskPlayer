package com.example.taskplayer.data.remote

import com.example.taskplayer.data.dto.AuthorizationRequest
import com.example.taskplayer.data.dto.AuthorizationResponse
import com.example.taskplayer.data.dto.FeelingsApiResponse
import com.example.taskplayer.data.dto.QuotesApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthRemoteSource {
    @POST("user/login")
    suspend fun login(@Body authorizationRequest: AuthorizationRequest): AuthorizationResponse

    @GET("feelings")
    suspend fun getFeelings(): FeelingsApiResponse

    @GET("quotes")
    suspend fun getQuotes(): QuotesApiResponse
}
