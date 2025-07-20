package com.example.taskplayer.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.taskplayer.R
import com.example.taskplayer.data.local.TokenManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    tokenManager: TokenManager) {
    Box(modifier = Modifier.fillMaxSize()) {
        // задник
        Image(
            painter = painterResource(R.drawable.forest_les),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        // лого
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
    LaunchedEffect(Unit) {
        try {
            delay(2000)
            Log.d("SplashScreen", "Checking login and onboarding status...")

            val isLoggedIn = tokenManager.isLoggedIn()
            val isOnboardingShown = tokenManager.isOnboardingShown()

            Log.d(
                "SplashScreen",
                "isLoggedIn = $isLoggedIn, isOnboardingShown = $isOnboardingShown"
            )
            when {
                tokenManager.isLoggedIn() -> onNavigateToMain()
                tokenManager.isOnboardingShown() -> onNavigateToLogin()
                else -> onNavigateToOnboarding()
            }
        } catch (e: Exception) {
            Log.e("SplashScreen", "Ошибка: ${e.message}", e)
        }

    }

}