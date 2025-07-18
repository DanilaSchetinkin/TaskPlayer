package com.example.taskplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.screen.OnBoarding
import com.example.taskplayer.screen.SignInScreen.SignInScreen
import com.example.taskplayer.screen.SplashScreen
import com.example.taskplayer.ui.theme.MediaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTheme {
                val tokenManager = TokenManager(this)
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ){
                    composable("splash"){
                        SplashScreen(navController, tokenManager)
                    }
                    composable("onboarding"){
                        OnBoarding(navController, tokenManager)
                    }
                    composable("login"){
                        SignInScreen(navController)
                    }

                }
            }
        }
    }
}