package com.example.taskplayer.presentation.main.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskplayer.R
import com.example.taskplayer.presentation.auth.component.AuthButton
import com.example.taskplayer.core.theme.MediaTheme

@Composable
fun OnBoarding(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Фоновое изображение
        Image(
            painter = painterResource(R.drawable.forest_les),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(130.dp))

            // Логотип
            Image(
                painter = painterResource(R.drawable.logo1),
                contentDescription = null,
                modifier = Modifier.size(width = 191.dp, height = 199.dp),
                contentScale = ContentScale.Fit
            )

            // Текстовый блок
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "ПРИВЕТ",
                    style = MediaTheme.typography.alegreyaBoldStart
                )
                listOf(
                    "Наслаждайся отборочными.",
                    "Будь внимателен.",
                    "Делай хорошо."
                ).forEach { text ->
                    Text(
                        text = text,
                        style = MediaTheme.typography.alegreyaSans20
                    )
                }
            }

            Spacer(modifier = Modifier.height(120.dp))

            // Основная кнопка
            AuthButton(
                onClick = onLoginClick,
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Text(
                    text = "Войти в аккаунт",
                    style = MediaTheme.typography.alegreyaSans25
                )
            }
        }
    }
}


