package com.example.taskplayer.data.remote


import com.example.taskplayer.data.remote.auth.AuthRemoteSource
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


object RetrofitClient {
    private const val URL = "http://mskko2021.mad.hakta.pro/api"
    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(Json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
        .build()
    val authService: AuthRemoteSource by lazy {
        retrofit.create(AuthRemoteSource::class.java)
    }
}