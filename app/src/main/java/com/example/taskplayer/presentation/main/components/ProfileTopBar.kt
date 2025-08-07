package com.example.taskplayer.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskplayer.R
import com.example.taskplayer.core.theme.MediaTheme
import com.example.taskplayer.data.local.UserSessionManager

@Composable
fun ProfileTopBar(
    tokenManager: UserSessionManager,
    onLogout: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.tripalka),
            contentDescription = "palki",
            modifier = Modifier.size(20.dp)
        )
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "LogoMain",
            modifier = Modifier.size(100.dp)
        )
        TextButton(onClick = onLogout) {
            Text(
                text = "exit",
                style = MediaTheme.typography.alegreyaSans15,
                color = MediaTheme.colors.text
            )
        }
    }
}