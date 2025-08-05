package com.example.taskplayer.data.remote

import com.example.taskplayer.data.local.UserSessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: UserSessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.url.encodedPath.contains("user/login")){
            return chain.proceed(originalRequest)
        }

        val token = tokenManager.getToken() ?: return chain.proceed(originalRequest)

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}