package com.example.taskplayer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplayer.presentation.main.MainScreen
import com.example.taskplayer.presentation.main.profile.ProfileScreen
import com.example.taskplayer.presentation.main.photo.PhotoScreen
import com.example.taskplayer.presentation.auth.SignInScreen
import com.example.taskplayer.presentation.main.splash.SplashScreen
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.taskplayer.AppNavigatorImpl
import com.example.taskplayer.Routes
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.presentation.main.components.BottomBar
import com.example.taskplayer.presentation.main.onboarding.OnBoarding

@Composable
fun AppNavigation(
    tokenManager: UserSessionManager,
    navController: NavHostController = rememberNavController()
) {
    val navigator = remember { AppNavigatorImpl(navController) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(Routes.MAIN, Routes.PROFILE)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        when (route) {
                            Routes.MAIN -> navigator.navigateToMain()
                            Routes.PROFILE -> navigator.navigateToProfile()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onNavigateToMain = { navigator.navigateToMain() },
                    onNavigateToLogin = { navigator.navigateToLogin() },
                    onNavigateToOnboarding = { navigator.navigateToOnboarding() },
                    tokenManager = tokenManager
                )
            }

            composable(Routes.ONBOARDING) {
                OnBoarding(
                    onLoginClick = { navigator.navigateToLogin() }
                )
            }

            composable(Routes.LOGIN) {
                SignInScreen(
                    tokenManager = tokenManager,
                    onNavigateToMain = { navigator.navigateToMain() }
                )
            }

            composable(Routes.MAIN) {
                MainScreen(
                    onNavigateToPhoto = { path -> navigator.navigateToPhoto(path) },
                    modifier = Modifier
                )
            }

            composable(Routes.PROFILE) {
                ProfileScreen(
                    onNavigateToPhoto = { path -> navigator.navigateToPhoto(path) },
                    tokenManager = tokenManager, // Добавлено передачу tokenManager
                    modifier = Modifier
                )
            }

            composable(Routes.PHOTO_SCREEN) { backStackEntry ->
                val path = backStackEntry.arguments?.getString("path") ?: ""
                PhotoScreen(
                    path = path,
                    onBack = { navigator.navigateBack() },
                    onDelete = { navigator.navigateBack() }
                )
            }
        }
    }
}