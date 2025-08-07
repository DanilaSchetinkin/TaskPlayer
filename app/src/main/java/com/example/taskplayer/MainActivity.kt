package com.example.taskplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.presentation.main.MainScreen
import com.example.taskplayer.presentation.main.photo.PhotoScreen
import com.example.taskplayer.presentation.main.profile.ProfileScreen
import com.example.taskplayer.presentation.auth.SignInScreen
import com.example.taskplayer.core.theme.MediaTheme
import com.example.taskplayer.navigation.AppNavigation
import com.example.taskplayer.presentation.main.components.BottomBar
import com.example.taskplayer.presentation.main.onboarding.OnBoarding
import com.example.taskplayer.presentation.main.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Сначала создаем UserSessionManager
        val tokenManager = UserSessionManager(applicationContext)

        // Затем инициализируем RetrofitClient с tokenManager
        RetrofitClient.initialize(tokenManager)

        setContent {
            MediaTheme {
                AppNavigation(tokenManager = tokenManager)
            }
        }
    }
}

