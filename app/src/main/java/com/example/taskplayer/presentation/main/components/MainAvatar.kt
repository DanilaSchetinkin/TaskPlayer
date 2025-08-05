package com.example.taskplayer.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.taskplayer.R
import com.example.taskplayer.data.local.UserSessionManager

@Composable
fun MainAvatar(tokenManager: UserSessionManager, modifier: Modifier = Modifier) {
    val avatarUrl = tokenManager.getAvatar()

    if (!avatarUrl.isNullOrEmpty()) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = "Avatar",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Image(
            painter = painterResource(R.drawable.billy),
            contentDescription = "Billy"
        )
    }
}