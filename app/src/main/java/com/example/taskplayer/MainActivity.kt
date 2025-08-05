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
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.presentation.main.MainScreen
import com.example.taskplayer.presentation.main.photo.PhotoScreen
import com.example.taskplayer.presentation.main.profile.ProfileScreen
import com.example.taskplayer.presentation.auth.SignInScreen
import com.example.taskplayer.core.theme.MediaTheme
import com.example.taskplayer.presentation.main.onboarding.OnBoarding
import com.example.taskplayer.presentation.main.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        RetrofitClient.initialize(this)
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
//    requireNotNull(context) { "Context не может быть null!" }
    val tokenManager = remember { UserSessionManager(context) }

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
                navController = navController
            )
        }

        composable("login") {
            SignInScreen(
                navController = navController,
                tokenManager = tokenManager,
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            MainScreen(
                navController = navController,
                tokenManager = tokenManager
            )
        }

        composable("profile") {
            ProfileScreen(
                navController = navController,
                tokenManager = tokenManager
            )
        }
        composable("photo_screen/{path}") {
            backStackEntry ->
            val path = backStackEntry.arguments?.getString("path") ?: ""
            PhotoScreen(
                path = path,
                navController = navController,
                tokenManager = tokenManager

            )
        }

    }
}