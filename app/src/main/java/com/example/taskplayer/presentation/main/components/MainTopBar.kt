package com.example.taskplayer.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskplayer.R
import com.example.taskplayer.data.local.UserSessionManager

@Composable
fun MainTopBar(tokenManager: UserSessionManager) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
            .padding(horizontal = 30.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.tripalka),
            contentDescription = "palki",
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterStart)
        )
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "LogoMain",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
        )
        MainAvatar(
            tokenManager = tokenManager,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .align(Alignment.CenterEnd)
        )
    }
}