package com.example.taskplayer.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskplayer.data.local.TokenManager

@Composable
fun MainScreen(tokenManager: TokenManager){
    Column(modifier = Modifier.fillMaxSize()) {
        Text("MainScreen")
    }
}