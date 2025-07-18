package com.example.taskplayer.screen

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
fun SplashScreen(navController: NavController, tokenManager: TokenManager) {
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
        delay(4000)

        navController.navigate(
            when {
                tokenManager.isLoggedIn() -> "main"
                tokenManager.isOnboardingShown() -> "login"
                else -> "onboarding"
            }
        ) {
            popUpTo("splash") { inclusive = true }
        }

    }

}