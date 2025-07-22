package com.example.taskplayer.screen.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taskplayer.R
import com.example.taskplayer.data.local.TokenManager

@Composable
fun MainAvatar(tokenManager: TokenManager, modifier: Modifier = Modifier){
    val avatarUrl = tokenManager.getAvatar()

    if (!avatarUrl.isNullOrEmpty()){
        AsyncImage(
            model = avatarUrl,
            contentDescription = "Avatar",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Image(painter = painterResource(R.drawable.billy),
            contentDescription = "Billy")
    }
}