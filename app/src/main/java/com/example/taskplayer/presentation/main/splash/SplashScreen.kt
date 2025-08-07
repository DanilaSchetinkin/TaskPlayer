package com.example.taskplayer.presentation.main.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.taskplayer.R
import com.example.taskplayer.data.local.UserSessionManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    tokenManager: UserSessionManager,
) {
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
        delay(2000) // Задержка для сплэш-скрина

        when {
            tokenManager.isLoggedIn() -> onNavigateToMain()
            tokenManager.isOnboardingShown() -> onNavigateToLogin()
            else -> onNavigateToOnboarding()
        }

    }

}