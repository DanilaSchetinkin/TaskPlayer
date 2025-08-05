package com.example.taskplayer.data.remote


import android.content.Context
import com.example.taskplayer.data.local.UserSessionManager
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val URL = "http://mskko2021.mad.hakta.pro/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private lateinit var tokenManager: UserSessionManager

    fun initialize(context: Context) {
        tokenManager = UserSessionManager(context)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(AuthInterceptor(tokenManager))
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()


    private val retrofit by lazy {
        Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .addConverterFactory(Json.asConverterFactory("application/json;".toMediaType()))
        .build()
    }

    val authService: AuthRemoteSource by lazy {
        retrofit.create(AuthRemoteSource::class.java)
    }
}