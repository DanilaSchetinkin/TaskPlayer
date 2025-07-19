package com.example.taskplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.screen.*
import com.example.taskplayer.screen.SignInScreen.SignInScreen
import com.example.taskplayer.ui.theme.MediaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    requireNotNull(context) { "Context не может быть null!" }
    val tokenManager = remember { TokenManager(context) }

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate("onboarding") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                tokenManager = tokenManager
            )
        }

        composable("onboarding") {
            OnBoarding(
                onNavigateToLogin = {
                    tokenManager.setOnboardingShown()
                    navController.navigate("login")
                },
                tokenManager = tokenManager,
                navController = navController
            )
        }

        composable("login") {
            SignInScreen(
                navController = navController,
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                tokenManager = tokenManager
            )
        }

        composable("main") {
            MainScreen(
                tokenManager = tokenManager
            )
        }
    }
}