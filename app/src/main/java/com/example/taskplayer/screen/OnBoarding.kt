package com.example.taskplayer.screen

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
import com.example.taskplayer.component.AuthButton
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.ui.theme.MediaTheme

@Composable
fun OnBoarding(
    onNavigateToLogin: () -> Unit,
    tokenManager: TokenManager,
    navController: NavController) {
    //задник
    Image(
        painter = painterResource(R.drawable.forest_les),
        contentDescription = null,
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Spacer(modifier = Modifier.height(130.dp))

        Box(modifier = Modifier.size(width = 191.dp, height = 199.dp)) {
            //лого
            Image(
                modifier = Modifier
                    .matchParentSize(),
                contentScale = ContentScale.Fit,
                painter = painterResource(R.drawable.logo1),
                contentDescription = null,
            )
        }
        //Вступительный текст
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "ПРИВЕТ",
                style = MediaTheme.typography.alegreyaBoldStart,
                color = MediaTheme.colors.text
            )
            Text(
                text = "Наслаждайся отборочными.",
                style = MediaTheme.typography.alegreyaSans20,
                color = MediaTheme.colors.text
            )
            Text(
                text = "Будь внимателен.",
                style = MediaTheme.typography.alegreyaSans20,
                color = MediaTheme.colors.text
            )
            Text(
                text = "Делай хорошо.",
                style = MediaTheme.typography.alegreyaSans20,
                color = MediaTheme.colors.text
            )

        }

        Spacer(modifier = Modifier.height(120.dp))
        //кнопка и ссылка
        AuthButton(
            onClick = { navController.navigate("login")
                        onNavigateToLogin()}
        ) {
            Text("Войти в аккаунт", style = MediaTheme.typography.alegreyaSans25)
        }

        Row(modifier = Modifier.padding(top = 20.dp)) {
            Text(
                "Ещё нет аккаунта?",
                style = MediaTheme.typography.alegreyaSans20400,
                color = MediaTheme.colors.text
            )
            Text(
                "Зарегестрируйтесь",
                style = MediaTheme.typography.alegreyaSans20,
                color = MediaTheme.colors.text
            )
        }

    }

}


